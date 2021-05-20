package pfp.core

import cats.Monad

trait ApiLaws[F[_]] {

  protected def api: Api[F]

  protected implicit def M: Monad[F]

  def forCreatedInventoryYouShouldFindQuantityAndNoMore(
      movie: Movie,
      qty: Qty
  ): IsEq[F[(Boolean, Boolean, Boolean)]] = {
    val result = for {
      dvds    <- api.addInventory(movie, qty)
      success <- List.fill(qty)(()).traverse(_ => api.getDVD(movie.id))
      fail    <- api.getDVD(movie.id)
    } yield (dvds, success, fail)

    result.map { case (dvds, succ, fail) =>
      (
        succ.forall(_.isDefined),
        succ.flatten.toSet === dvds,
        fail.isDefined
      )
    } <-> M.pure((true, true, false))
  }

}

object ApiLaws {
  def apply[F[_]](alg: Api[F])(implicit ev: Monad[F]): ApiLaws[F] = new ApiLaws[F] {
    override protected val api: Api[F]          = alg
    override protected implicit val M: Monad[F] = ev
  }
}
