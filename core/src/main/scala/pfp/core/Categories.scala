package pfp.core

trait Categories[F[_]] {

  def findAll: F[List[Category]]

  def create(name: CategoryName): F[Unit]

}
