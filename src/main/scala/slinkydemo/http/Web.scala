package slinkydemo.http

import scala.xml.Elem
import scalaz._
import scalaz.http.response._
import scalaz.http.request._
import scalaz.http.scapps.Scapps._
import scalaz.http.scapps.ViewHelpers.Html._
import view.{DemoContent, HtmlOut}, HtmlOut._, DemoContent._
import scapps.util.Slinky._

object Web {
  def webRoot(request: Request[Stream]): Option[Response[Stream]] = {
    implicit val r = request
    val content =
        <div>
          <h2>APIs</h2>
          <p>{a("/api/", "API documentation")}</p>
        </div>
    htmlResponse(OK, DemoContent("Overview", content))
  }
}
