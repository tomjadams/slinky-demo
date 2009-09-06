package spec

import java.io.{ByteArrayOutputStream, File, FileInputStream}

object ReadableFile {
  implicit def fileToReadableFile(file: File): ReadableFile = ReadableFile(file)

  implicit def readableFileToFile(file: ReadableFile) = file match {
    case ReadableFile(f) => f
  }
}

final case class ReadableFile(file: File) {
  // Taken from: http://blog.lostlake.org/index.php?/archives/61-A-spell-checker-in-Scala.html
  def slurp: String = {
    val bos = new ByteArrayOutputStream
    val buffer = new Array[Byte](2048)
    val stream = new FileInputStream(file)

    def read {
      stream.read(buffer) match {
        case bytesRead if bytesRead < 0 =>
        case 0 => read
        case bytesRead => bos.write(buffer, 0, bytesRead); read
      }
    }

    read
    bos.toString("UTF-8")
  }
}
