package utils

/**
  * Type of the file to look up.
  * Internally, we will use "Resource", so we can go fetch from the project resources folder.
  * For the outside, we need to read files.
  */
object FileType extends Enumeration {
  type FileType = Value

  val Resource,
      File = Value
}