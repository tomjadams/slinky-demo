package slinkydemo.db

import com.novocode.squery.session._

// http://szeiger.de/blog/2008/12/21/a-type-safe-database-query-dsl-for-scala/#more-24
object Database {
  implicit lazy val sessionFactory: SessionFactory = new DriverManagerSessionFactory("org.h2.Driver", "jdbc:h2:/tmp/slinkydemo")
}
