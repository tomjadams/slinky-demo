package spec

sealed trait FilePath

private final case class FilePath_(path: String) extends FilePath {
  override def toString = path
}

// TODO Handle null for passed params correctly.
object FilePath {
  import java.io.File

  implicit def filepath(filePath: String): FilePath = FilePath_(filePath)

  implicit def filepath(file: File): FilePath = file.getCanonicalPath

  implicit def filePathToString(filePath: FilePath): String = filePath match {
    case FilePath_(p) => p
    case null => null
  }

  implicit def stringToFile(filePath: String): File = filePath match {
    case null => null
    case f => filePathToFile(filepath(f))
  }

  implicit def filePathToFile(filePath: FilePath): File = filePath match {
    case FilePath_(filePath) => new File(filePath)
    case null => null
  }
}
