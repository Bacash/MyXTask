import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import reactivemongo.api.MongoConnection
import reactivemongo.api.collections.bson.BSONCollection

object Conn {
  def dbFromConnection(connection: MongoConnection): Future[BSONCollection] =
    connection.database("Student").
      map(_.collection("students"))
}