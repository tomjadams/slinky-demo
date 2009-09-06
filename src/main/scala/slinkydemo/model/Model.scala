package slinkydemo.model

import com.novocode.squery.combinator.Table

final case class Person(id: Int, name: String, organisation: String)

object Person extends Table[(Int, String, String)]("people") {
  def id = column[Int]("id", O.AutoInc, O.NotNull)
  def name= column[String]("name",  O.NotNull)
  def organisation = column[String]("organisation",  O.NotNull)
  def * = id ~ name ~ organisation
}
