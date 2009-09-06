import sbt._
import sqm.migrate._
import sqm.sbt._

final class SlinkyDemoBuild(info: ProjectInfo) extends DefaultWebProject(info) with ScalaQueryMigrationProject {
  //  val servlet = "javax.servlet" % "servlet-api" % "2.5" % "provided->default"

  // Runtime dependencies
  val jettyServlet = "org.eclipse.jetty" % "jetty-servlet" % "7.0.0.RC2"
  val jettyServer = "org.eclipse.jetty" % "jetty-server" % "7.0.0.RC2"
  val dispatch = "net.databinder" %% "dispatch-json" % "0.5.1"
  val h2database = "com.h2database" % "h2" % "1.1.117"

  // Test dependencies
  val junit = "junit" % "junit" % "4.7" % "test->default"
  val specs = "org.scala-tools.testing" % "specs" % "1.5.0" % "test->default"
  val httpunit = "httpunit" % "httpunit" % "1.6.2" % "test->default"
  val rhino = "rhino" % "js" % "1.7R2" % "test->default"

  override def mainClass = Some("scapps.http.SlinkyRunner")

  override def migrations: List[DatabaseMigration] = Migrations.migrations

  override def sessionFactory = Migrations.sessionFactory
}
