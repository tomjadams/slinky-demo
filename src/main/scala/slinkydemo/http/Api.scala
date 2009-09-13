package slinkydemo.http

import _root_.scapps.{Json, Id}, Json._, Id._
import scala.xml.Elem
import scalaz._
import scalaz.http.response._
import scalaz.http.request._
import scalaz.http.scapps.Scapps._
import scalaz.http.scapps.ViewHelpers.Html._
import scapps.util.Slinky._
import model._
import view.{ConferenceContent, JsonOut, HtmlOut}, ConferenceContent._, JsonOut._, HtmlOut._

object Api {
  def apiUsage(request: Request[Stream]): Option[Response[Stream]] = {
    import _root_.slinkydemo.view.ConferenceContent

    implicit val r = request
    val content =
      <div>
        <h2>Register</h2>
        <p>Endpoint:{a("/api/register", "/api/register")}</p>
      </div>
    ConferenceContent("Conference API", content)
  }

  def apiRegister(request: Request[Stream]): Option[Response[Stream]] = {
    implicit val r = request
    val result = r.bodyAsJsValue.right.map(payload => {
      val PersonParser.name(name) = payload
      val PersonParser.organisation(organisation) = payload
      //    val ins1 = (Users.first ~ Users.last).insert("Homer", Some("Simpson"))
      (OK, Map('description -> "Person registration successful.", 'name -> name, 'organisation -> organisation))
    }).right.getOrElse((BadRequest, Map('description -> "Person registration failed.", 'error -> "Invalid JSON payload.")))
    jsonResponse(result._1, result._2)
  }

  def apiRegistrants(request: Request[Stream]): Option[Response[Stream]] = {
    implicit val r = request
    jsonResponse(OK, Map('registrants -> List("Slinky Malinky", "Butterball Brown")))
  }

  def apiSearch(request: Request[Stream]): Option[Response[Stream]] = {
    implicit val r = request
    jsonResponse(OK, Map('results -> List("Slinky Malinky", "Butterball Brown")))
  }
}

