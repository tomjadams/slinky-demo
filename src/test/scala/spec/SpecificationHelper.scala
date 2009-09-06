package spec

import java.io._
import java.net.URL
import scalaz.OptionW, OptionW._
import scalaz.Scalaz._
import ReadableFile._

object SpecificationHelper {
  def dataFileContents(path: String) = (dataFile(path): File).slurp

  def dataFileStream(path: String) = new FileInputStream(dataFile(path): String)

  def dataFile(path: String): FilePath = getResource(canonicalise(path)).err("No resource found at '" + path + "'")

  def getResource(path: String) = SpecificationHelper.getClass.getResource(path)

  def canonicalise(path: String) = if (path.startsWith("/")) path else "/" + path

  implicit def StringToInputStream(s: String): InputStream = new ByteArrayInputStream(s.getBytes("UTF-8"))

  private implicit def toFilePath(url: URL): FilePath = new File(url.toURI)

  private implicit def toOptionW[A](a: A): OptionW[A] = Some(a)
}
