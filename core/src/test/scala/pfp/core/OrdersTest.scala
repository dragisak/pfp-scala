package pfp.core

import cats.{Eq, Monad}
import org.scalacheck.Arbitrary
import org.scalacheck.Prop.forAll
import org.typelevel.discipline.Laws
import squants.Money
import cats.{Eq, Monad}
import org.typelevel.discipline.Laws
import cats.kernel.laws.discipline._
import org.scalacheck.Arbitrary
import org.scalacheck.Prop.forAll

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
      laws.getAfterCreate(_, _, _, _) _
    )
  )

}

object OrdersTest {
  def apply[F[_]: Monad](alg: Orders[F]): OrdersTest[F] = new OrdersTest[F] {
    override protected val laws: OrdersLaws[F] = OrdersLaws(alg)
  }
}
