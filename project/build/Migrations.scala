import com.novocode.squery.session._

object Migrations {
  lazy val sessionFactory: SessionFactory = new DriverManagerSessionFactory("org.h2.Driver", "jdbc:h2:/tmp/slinkydemo")

  lazy val migrations = List(DatabaseMigration0001)
}
