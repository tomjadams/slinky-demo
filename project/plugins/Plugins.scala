import sbt._

class Plugins(info: ProjectInfo) extends PluginDefinition(info) {
  val sqm = "sqm" % "sqm" % "0.0.1"
  val scalaquery = "empty" % "scalaquery" % "1.0-SNAPSHOT"
}

