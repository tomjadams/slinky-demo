package slinkydemo.model

import com.novocode.squery.combinator.Table
import dispatch.json.Js

object PersonParser extends Js {
  val name = 'name ? str
  val organisation = 'organisation ? str
}

// TODO Add to JSON serialisation for this type.
final case class Person(name: String, organisation: String)

object Person extends Table[(Int, String, String)]("people") {
  def id = column[Int]("id", O.AutoInc, O.NotNull)
  def name= column[String]("name",  O.NotNull)
  def organisation = column[String]("organisation",  O.NotNull)
  def * = id ~ name ~ organisation
}
