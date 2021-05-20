package pfp.core

import cats.{Eq, Monad}

trait ApiTest[F[_]] extends Laws {

  protected def laws: ApiLaws[F]

  def videoClubLaws(implicit
      arbMovie: Arbitrary[Movie],
      arbQty: Arbitrary[Qty],
      eqFIntPair: Eq[F[(Boolean, Boolean, Boolean)]]
  ): RuleSet = new SimpleRuleSet(
    "Video Club Laws",
    "Create inventory. Find qty of DVDs and no more" -> forAll(
      laws.forCreatedInventoryYouShouldFindQuantityAndNoMore _
    )
  )

}

object ApiTest {
  def apply[F[_]: Monad](alg: Api[F]): ApiTest[F] = new ApiTest[F] {
    override protected val laws: ApiLaws[F] = ApiLaws(alg)
  }
}
