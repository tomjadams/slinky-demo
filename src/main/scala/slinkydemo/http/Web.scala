package slinkydemo.http

import scala.xml.Elem
import scalaz._
import scalaz.http.response._
import scalaz.http.request._
import scalaz.http.scapps.Scapps._
import scalaz.http.scapps.ViewHelpers.Html._
import view.View._, view.ViewHelpers._

object Web {
  def webRoot(request: Request[Stream]): Option[Response[Stream]] = {
    implicit val r = request
    implicit val header: Option[Elem] = None
    val body =
        <div>
          <h1>Overview</h1>
          <p>Slinky is the Scalaz 4 HTTP library.</p>
          <h1>APIs</h1>
          <p>{a("/api/", "API documentation")}</p>
        </div>

    Some(htmlResponse(OK, html(title("Overview"), body)))
  }
}
