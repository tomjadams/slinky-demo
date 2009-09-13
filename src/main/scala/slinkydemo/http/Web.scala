package slinkydemo.http

import scala.xml.Elem
import scalaz._
import scalaz.http.response._
import scalaz.http.request._
import scalaz.http.scapps.Scapps._
import scalaz.http.scapps.ViewHelpers.Html._
import view.{ConferenceContent, HtmlOut}, HtmlOut._, ConferenceContent._
import scapps.util.Slinky._

object Web {
  def webRoot(request: Request[Stream]): Option[Response[Stream]] = {
    implicit val r = request
    val content =
        <div>
          <h2>Registration</h2>
          <p>Register for the conference below.</p>
          <form method="post" action="/register">
            <div><input type="text" name="name" placeholder="Enter your name..."/></div>
            <div><input type="text" name="organisation" placeholder="Enter your organisation..."/></div>
            <div><input type="submit" value="Register"/></div>
          </form>
        </div>
    ConferenceContent("Overview", content)
  }
}
