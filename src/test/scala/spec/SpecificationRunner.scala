package spec

import com.googlecode.instinct.locate.ContextFinderImpl
import com.googlecode.instinct.runner.TextRunner
import java.io.File

object SpecificationRunner {
  private lazy val contextFinder = new ContextFinderImpl(SpecificationRunner.getClass)

  def main(args: Array[String]) {
    val classes = contextFinder.getContextNames().map(name => Class.forName(name.getFullyQualifiedName))
    TextRunner.runContexts(classes: _*)
  }
}
