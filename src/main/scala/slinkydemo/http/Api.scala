package slinkydemo.http

import _root_.scapps.{Json, Id}, Json._, Id._
import dispatch.json._, Js._
import scala.xml.Elem
import scalaz._
import scalaz.http.response._
import scalaz.http.request._
import scalaz.http.scapps.Scapps._
import scalaz.http.scapps.ViewHelpers.Html._
import view.View._, view.ViewHelpers._

object Person extends Js {
  val name = 'name ? str
  val organisation = 'organisation ? str
}

// TODO For HTML snippets see http://technically.us/code/x/weaving-tweed-with-scala-and-json "What to do with case classes if you aren't making a calculator"
object Api {
  def apiUsage(request: Request[Stream]): Option[Response[Stream]] = {
    implicit val r = request
    implicit val header: Option[Elem] = None
    val body =
      <div>
        <h1>Slinky Demo API</h1>
        <h2>Register</h2>
        <p>Endpoint:{a("/api/register", "/api/register")}</p>
      </div>
    Some(htmlResponse(OK, html(title("API Usage"), body)))
  }

  def apiRegister(request: Request[Stream]): Option[Response[Stream]] = {
    implicit val r = request

    // TODO Handle stuff not being there.
    val result = bodyAsJsValue.right.map(payload => {
      val Person.name(name) = payload
      val Person.organisation(organisation) = payload

      //    val ins1 = (Users.first ~ Users.last).insert("Homer", Some("Simpson"))

      (OK, Map('description -> "Person registration successful.", 'name -> name, 'organisation -> organisation))
    }).right.getOrElse((BadRequest, Map('description -> "Person registration failed.", 'error -> "Invalid JSON payload.")))

    Some(jsonResponse(result._1, result._2))
  }

  def apiRegistrants(request: Request[Stream]): Option[Response[Stream]] = {
    implicit val r = request
    Some(jsonResponse(OK, Map('registrants -> List("Slinky Malinky", "Butterball Brown"))))
  }

  def apiSearch(request: Request[Stream]): Option[Response[Stream]] = {
    implicit val r = request
    Some(jsonResponse(OK, Map('results -> List("Slinky Malinky", "Butterball Brown"))))
  }

  // TODO Move these into helpers?
  private def bodyAsJsValue(implicit r: Request[Stream]) = parseJson(bodyAsString(r))

  private def bodyAsString(implicit r: Request[Stream]): String = r.body.map(_.toChar).mkString
}

