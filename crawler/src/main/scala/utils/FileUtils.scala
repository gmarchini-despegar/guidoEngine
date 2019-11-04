package utils

import scala.io.BufferedSource
import scala.io.Source.fromInputStream

/**
  * Utils for file reading.
  */
object FileUtils {

  /**
    * Gets the content of a resource file as a string.
    *
    * @param filename The filename.
    * @return A string with the contents of the file.
    */
  def getResourceFileAsString(filename: String) = getBufferAsString(fromInputStream(getClass.getResourceAsStream(filename), "UTF8"))

  /**
    * Gets the content of a buffer as a string.
    *
    * @param buffer A buffer.
    * @return A string with the contents of the buffer.
    */
  private def getBufferAsString(buffer: BufferedSource): String = try buffer.mkString finally buffer.close
}
