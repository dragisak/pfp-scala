package pfp.core

trait PaymentClient[F[_]] {
  def process(payment: Payment): F[PaymentId]
}
