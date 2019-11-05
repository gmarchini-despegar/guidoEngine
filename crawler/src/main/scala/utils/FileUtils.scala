package utils

import utils.FileType.FileType

import scala.io.{BufferedSource, Source}

/**
  * Utils for file reading.
  */
class FileUtils(fileType: FileType){

  /**
    * Gets the content of a resource file as a string.
    *
    * @param filename The filename.
    * @return A string with the contents of the file.
    */
  def getResourceFileAsString(filename: String): String ={
    val buffer: BufferedSource = fileType match {
      case FileType.Resource =>
        Source.fromResource(filename)

      case FileType.File =>
        Source.fromFile(filename)
    }

    getBufferAsString(buffer)
  }

  /**
    * Gets the content of a buffer as a string.
    *
    * @param buffer A buffer.
    * @return A string with the contents of the buffer.
    */
  private def getBufferAsString(buffer: BufferedSource): String =
    try buffer.mkString
    finally buffer.close
}

object FileUtils {
  /**
    * FileUtils for internal use (fetches files from resource files)
    */
  def internalFileUtils: FileUtils =
    new FileUtils(FileType.Resource)

  /**
    * FileUtils for external use (fetches files from an absolute computer directory)
    */
  def externalFileUtils: FileUtils =
    new FileUtils(FileType.File)
}