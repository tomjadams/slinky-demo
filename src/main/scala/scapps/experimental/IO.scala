package scapps.experimental.rlib

import scala.io._

object IO {

  def readLines(fileName : String) = Source.fromFile(fileName).getLines

  def foreach(fileName : String)(f : String => Unit) {
    readLines(fileName).foreach(f)
  }

  def read(fileName : String) = readLines(fileName).foldLeft("")((_ : String) + (_ : String))
}
