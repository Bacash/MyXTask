package controllers

import javax.inject._

import models.GetStarted.Student
import models._
import play.api._
import play.api.mvc._
import reactivemongo.api.MongoConnection
import scala.concurrent.duration.DurationInt

import scala.concurrent.{Await, Future}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() extends Controller {

  val driver1 = new reactivemongo.api.MongoDriver

  val connection3 = driver1.connection(List("localhost"))

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {

    val list = Await.result(GetStarted.findAll(), 10.second).map(student => student.toString)

    Ok(views.html.index("Koookkkks.", list))
  }

}
