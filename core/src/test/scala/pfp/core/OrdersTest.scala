package pfp.core

import cats.kernel.laws.discipline._
import cats.{Eq, Monad}
import org.scalacheck.Arbitrary
import org.scalacheck.Prop.forAll
import org.typelevel.discipline.Laws
import squants.Money

trait OrdersTest[F[_]] extends Laws {

  protected def laws: OrdersLaws[F]

  def ordersLaws(implicit
      arbUserId: Arbitrary[UserId],
      arbPaymentId: Arbitrary[PaymentId],
      arbItems: Arbitrary[List[CartItem]],
      arbTotal: Arbitrary[Money],
      eqFIntPair: Eq[F[Boolean]]
  ): RuleSet = new SimpleRuleSet(
    "Orders Laws",
    "If I create an order, I should be able to get the order" -> forAll(
      laws.getAfterCreate _
    ),
    "If I create an order, I should be able to find the order" -> forAll(
      laws.findByAfterCreate _
    )
  )

}

object OrdersTest {
  def apply[F[_]: Monad](alg: Orders[F]): OrdersTest[F] = new OrdersTest[F] {
    override protected val laws: OrdersLaws[F] = OrdersLaws(alg)
  }
}
