package slinkydemo.http

import Web._, Api._
import scalaz._
import scalaz.http.servlet.{HttpServletRequest}
import scalaz.http.response._
import scalaz.http.request._
import scalaz.http.scapps.Route._
import scalaz.http.scapps.Scapps._
import scalaz.http.scapps.{BaseApp, Route}

// TODO Conference registration. Register name & organisation, see who else is registered. Search. API for it all.
final class SlinkyDemoApplication extends BaseApp {
  val routes: Kleisli[Option, Request[Stream], Response[Stream]] =
      List(
        exactPath("/") >=> GET >=> webRoot _,
        startsWith("/api") >=> List(
          exactPath("/") >=> GET >=> apiUsage _,
          startsWith("/register") >=> POST >=> apiRegister _,
          startsWith("/registrants") >=> GET >=> apiRegistrants _,
          startsWith("/search") >=> GET >=> apiSearch _
        )
      )

  def route(implicit request: Request[Stream], servletRequest: HttpServletRequest) = routes(request)
}
