package pfp.core

import cats.Monad
import cats.kernel.laws._
import cats.laws.IsEq
import cats.syntax.flatMap._
import cats.syntax.functor._
import squants.Money

trait OrdersLaws[F[_]] {

  protected def api: Orders[F]

  protected implicit def M: Monad[F]

  def getAfterCreate(
      userId: UserId,
      paymentId: PaymentId,
      items: List[CartItem],
      total: Money
  ): IsEq[F[Boolean]] = {

    val result = for {
      orderId    <- api.create(userId, paymentId, items, total)
      maybeOrder <- api.get(userId, orderId)
    } yield maybeOrder

    result.map(maybeOrder => maybeOrder.isDefined) <-> M.pure(true)
  }

}

object OrdersLaws {
  def apply[F[_]](alg: Orders[F])(implicit ev: Monad[F]): OrdersLaws[F] = new OrdersLaws[F] {
    override protected val api: Orders[F]       = alg
    override protected implicit val M: Monad[F] = ev
  }
}
