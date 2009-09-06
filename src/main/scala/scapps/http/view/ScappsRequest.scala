package com.scapps.appengine

import xml.Elem
import scalaz.http.request.Request
import scalaz.http.response.Response
import scalaz.Scalaz._
import scalaz.http.response._
import scalaz.http.request.{POST, Method, Request, GET}
import scalaz.http.servlet.{HttpServlet, HttpServletRequest, ServletApplication, StreamStreamServletApplication}
import scalaz.http.servlet.HttpServlet._
import scalaz.http.StreamStreamApplication._
import scalaz.http.{Application, ContentType}
import scalaz.http.response.xhtml.Doctype.{transitional, strict}
import scalaz.http.response._

case class ScappsRequest(request : Request[Stream]) {
  import slinkydemo.view.View
  import scalaz.http.response.Body._
  import scalaz.http.response.xhtml.Doctype._

  implicit val implicitRequest = request
  implicit def charSet = scalaz.CharSet.UTF8

//  def accessDenied : Response[Stream] = render(View.doc("access denied", "login, dummy.", request))

//  def authenticated[B](f : User => B) = AppEngineAuth.user |> f

//  def ensureAuthenticated(f : User => Option[Response[Stream]]) : Option[Response[Stream]] = {
//    authenticated(f) getOrElse Some(accessDenied)
//  }

//  def doc[A](title : String, content : A) = render(View.doc(title, content, request))

  def render(content: Elem) = OK(ContentType, "text/html") << transitional << content

  def fourOhFour = NotFound(ContentType, "text/html") << transitional << "404 - Not found"

  def respondWith(ct: String, content: String) = OK(ContentType, ct) << content
}

object ScappsRequest {
  implicit def scalazRequestToRequest(s : Request[Stream]) = ScappsRequest(s)
}
