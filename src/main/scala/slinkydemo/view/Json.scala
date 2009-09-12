package slinkydemo.view

import _root_.scapps.{Json, Id}, Json._, Id._
import scalaz.http.request._

trait JsonRquestImplicit {
  implicit def RequestToJsonRequest(r: Request[Stream]) = JsonRequest(r)
}

final case class JsonRequest(request: Request[Stream]) extends JsonRquestImplicit {

  def bodyAsJsValue = parseJson(bodyAsString)

  private def bodyAsString = request.body.map(_.toChar).mkString
}

object JsonOut {
  import MimeType._
  import _root_.scapps.{Json, Id}, Json._, Id._
  import scalaz.http.scapps.Scapps._
  import scalaz.http._, StreamStreamApplication._
  import scalaz.http.response._
  import scalaz.Scalaz._

  implicit val charSet = scalaz.CharSet.UTF8

  def jsonResponse[T](status: Status, payload: T)(implicit request: Request[Stream], jsonValuer: Json[T]) = status(ContentType, JsonMimeType) << payload.jsonString
}
