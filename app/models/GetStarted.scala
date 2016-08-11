package models

import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.api.{DefaultDB, MongoConnection, MongoDriver}
import reactivemongo.bson.{BSONDocumentReader, BSONDocumentWriter, Macros, document}

import scala.concurrent.{ExecutionContext, Future}

object GetStarted {
  // My settings (see available connection options)
  val mongoUri = "mongodb://localhost:27017/"

  import ExecutionContext.Implicits.global // use any appropriate context

  // Connect to the database: Must be done only once per application
  val driver = MongoDriver()
  val parsedUri = MongoConnection.parseURI(mongoUri)
  val connection = parsedUri.map(driver.connection(_))

  // Database and collections: Get references
  val futureConnection = Future.fromTry(connection)
  def db1: Future[DefaultDB] = futureConnection.flatMap(_.database("Student"))
  def studentCollection: Future[BSONCollection] = db1.map( defaultDB =>  defaultDB.collection("students"))

  // Write Documents: insert or update

  implicit def studentWriter: BSONDocumentWriter[Student] = Macros.writer[Student]
  /* or provide a custom one

  def createPerson(person: Person): Future[Unit] =
    personCollection.flatMap(_.insert(person).map(_ => {})) // use personWriter

  def updatePerson(person: Person): Future[Int] = {
    val selector = document(
      "firstName" -> person.firstName,
      "lastName" -> person.lastName
    )

    // Update the matching person
    personCollection.flatMap(_.update(selector, person).map(_.n))
  }
*/
  implicit def studentReader: BSONDocumentReader[Student] = Macros.reader[Student]
  // or provide a custom one

 def findAll(): Future[List[Student]] =
   studentCollection.flatMap(_.find(document).
     cursor[Student]().collect[List]())

 def findPersonByAge(age: Int): Future[List[Student]] =
    studentCollection.flatMap(_.find(document("year" -> age)). // query builder
      cursor[Student]().collect[List]()) // collect using the result cursor
  // ... deserializes the document using personReader

  // Custom persistent types
  case class Student(name: String, indexno: Double, year: Double, yearofbirth: String)
}