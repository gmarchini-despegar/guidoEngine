package crawler

import crawler.parsing.{NodeParser, NodeWithParents}
import utils.FileUtils

class Crawler(okButtonParser: NodeParser) {
  private final val standartOkButtonId = "make-everything-ok-button"

  def findOkButtonByOriginalId(originalFilePath: String, secondaryFilePath: String, okButtonId: String): String = {
    okButtonParser.findNodeById(fileAsString(originalFilePath), okButtonId) match {
      case Some(originalButtonNode) =>
        lookupButtonInNextHtml(secondaryFilePath, originalButtonNode)

      case None =>
        throw new RuntimeException("OK button not found in original file")
    }
  }

  /**
    * Finds the ok button from the original file, and then finds the best match from the second file.
    * Returns the path to the OK button
    */
  def findOkButton(originalFilePath: String, secondaryFilePath: String): String = {
    okButtonParser.findNodeById(fileAsString(originalFilePath), standartOkButtonId) match {
        case Some(originalButtonNode) =>
          lookupButtonInNextHtml(secondaryFilePath, originalButtonNode)

        case None =>
          throw new RuntimeException("OK button not found in original file")
      }
  }

  /**
    * Looks up the best match from the ok button in the second file
    * @param secondaryFilePath
    * @param originalButtonNode
    * @return
    */
  private def lookupButtonInNextHtml(secondaryFilePath: String, originalButtonNode: NodeWithParents) = {
    val bestMatch =
      okButtonParser.findSimilarNodes(
        html = fileAsString(secondaryFilePath),
        nodeToSearch = originalButtonNode
      ).maxBy { nodeWithParents =>
        originalButtonNode.attributes.count {
          case (attributeKey, attributeValue) =>
            nodeWithParents.attributes.get(attributeKey).contains(attributeValue)
        }
      }

    pathToString(bestMatch)
  }

  private def fileAsString(path: String): String = {
    FileUtils.getResourceFileAsString(path)
  }

  /**
    * Makes the path to the given node by the form <tag> > [...]
    */
  private def pathToString(okButton: NodeWithParents): String = {
    okButton.parents.mkString(" > ")
  }



}
