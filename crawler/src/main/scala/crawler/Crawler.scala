package crawler

import crawler.parsing.{NodeWithParents, OkButtonParser}
import utils.FileUtils

class Crawler(okButtonParser: OkButtonParser) {

  /**
    * Finds the ok button from the original file, and then finds the best match from the second file.
    * Returns the path to the OK button
    */
  def findOkButton(originalFilePath: String, secondaryFilePath: String): String = {
      originalOkButton(originalFilePath) match {
        case Some(originalButtonNode) =>
          val bestMatch =
          lookupOkButtonIn(originalButtonNode, secondaryFilePath).maxBy{ nodeWithParents =>
            originalButtonNode.attributes.count {
              case (attributeKey, attributeValue) =>
                nodeWithParents.attributes.get(attributeKey).contains(attributeValue)
            }
          }

          pathToString(bestMatch)

        case None =>
          throw new RuntimeException("OK button not found in original file")
      }
  }

  /**
    * Fetches the original ok button from the given file.
    * @param originalFilePath
    * @return
    */
  private def originalOkButton(originalFilePath: String): Option[NodeWithParents] = {
    okButtonParser.findOkButton(fileAsString(originalFilePath))
  }

  /**
    * Given the original ok button, fetches a similar button from the second file path.
    * @param originalButtonNode
    * @param secondaryFilePath
    * @return
    */
  private def lookupOkButtonIn(originalButtonNode: NodeWithParents, secondaryFilePath: String): Seq[NodeWithParents] = {
    okButtonParser.findSimilarButton(fileAsString(secondaryFilePath), originalButtonNode)
  }

  private def fileAsString(path: String): String = {
    FileUtils.getResourceFileAsString(path)
  }

  /**
    * Makes the path to the given node by the form <tag> > [...]
    *
    * @param okButton
    * @return
    */
  private def pathToString(okButton: NodeWithParents): String = {
    okButton.parents.mkString(" > ")
  }



}
