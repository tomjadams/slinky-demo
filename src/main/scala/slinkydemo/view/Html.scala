package slinkydemo.view

// Some code stolen from http://technically.us/code/x/weaving-tweed-with-scala-and-json

// TODO Move into scapps/slinky.

import scala.xml.{Elem, NodeSeq}
import scalaz.http.request._

trait HtmlConvertable {
  import scalaz.http.response._
  import HtmlOut._

  implicit def ContentToHtmlable(content: HtmlContent): Htmlable = Page(content)

  implicit def ContentToResponse(content: HtmlContent)(implicit request: Request[Stream]): Response[Stream] = htmlResponse(OK, ContentToHtmlable(content))

  implicit def ContentToOptionResponse(content: HtmlContent)(implicit request: Request[Stream]): Option[Response[Stream]] = Some(ContentToResponse(content))
}

object HtmlOut extends HtmlConvertable {
  import MimeType._
  import scalaz.http.scapps.Scapps._
  import scalaz.http._, StreamStreamApplication._
  import scalaz.http.response._
  import scalaz.http.response.xhtml.Doctype._
  import scalaz.Scalaz._

  implicit val charSet = scalaz.CharSet.UTF8

  def htmlResponse(status: Status, htmlable: Htmlable)(implicit request: Request[Stream]) = status(ContentType, HtmlMimeType) << transitional << htmlable.html
}

trait HtmlContent {
  def head: NodeSeq
  def body: Elem
}

trait Htmlable {
  val html: Elem
}

final case class Page(content: HtmlContent) extends Htmlable {
  val html =
      <html xmlns="http://www.w3.org/1999/xhtml">
      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        {content.head}
      </head>
      <body>
        {content.body}
      </body>
      </html>
}
