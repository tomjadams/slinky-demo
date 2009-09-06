package scapps.util

import scalaz.Scalaz._
import scalaz.{Validation, Success, Failure}

case class StringParser(s : String) {
  def parseLong: Validation[NumberFormatException, Long] = {
    (() => java.lang.Long.parseLong(s)).throws.left.map(_.asInstanceOf[NumberFormatException])
  }.fold(Failure(_), Success(_))
  
  def asLong: Option[Long] = parseLong.either.right.toOption
}

object StringParser {
  implicit def StringTo(s : String) = StringParser(s)
  implicit def StringFrom(p : StringParser) = p.s
}
