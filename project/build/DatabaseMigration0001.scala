import com.novocode.squery.combinator.Table
import sqm.migrate._

// TODO Find a way to share this with the main code.
object Person extends Table[(Int, String, String)]("people") {
  def id = column[Int]("id", O.AutoInc, O.NotNull)
  def name= column[String]("name",  O.NotNull)
  def organisation = column[String]("organisation",  O.NotNull)
  def * = id ~ name ~ organisation
}

object DatabaseMigration0001 extends DatabaseMigration {
  import com.novocode.squery.combinator.Implicit._
  import com.novocode.squery.session.SessionFactory, SessionFactory._
  import com.novocode.squery.simple.StaticQueryBase._

  override def version = 1
  override def description = Some("Initialise devices table")

  override def up {
    Devices.createTable
  }

  override def down {
    updateNA("drop table devices")
  }
}
