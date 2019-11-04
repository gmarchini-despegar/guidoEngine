package crawler.parsing

import scala.annotation.tailrec
import scala.xml.{Elem, Node, XML}

class OkButtonParser {

  /**
    * finds the standart ok button
    */
  def findOkButton(html: String): Option[NodeWithParents] = {
    val xml: Elem = XML.loadString(html)
    val xmlAsNodeWithParents = new NodeWithParents(xml, Seq())

    findSimilarButtonRec(
      nodes = Seq(xmlAsNodeWithParents),
      okButtonAttributes = Map("id" -> "make-everything-ok-button")
    ).headOption
  }

  /**
    * given a Node, finds the first node that has any of its attributes.
    * This is a BFS function.
    */
  def findSimilarButton(html: String,
                        originalOkButton: NodeWithParents
                       ): Seq[NodeWithParents] = {
    val xml: Elem = XML.loadString(html)
    val xmlAsNodeWithParents = new NodeWithParents(xml, Seq())

    findSimilarButtonRec(
      nodes = Seq(xmlAsNodeWithParents),
      okButtonAttributes = originalOkButton.attributes
    )
  }

  /**
    * Finds a button that has any of the attributes passed by parameter.
    * If found, returns the NodeWithParents.
    */
  @tailrec private def findSimilarButtonRec(nodes: Seq[NodeWithParents],
                                            okButtonAttributes: Map[String, String],
                                            foundNodes: Seq[NodeWithParents] = Seq()
                               ): Seq[NodeWithParents] = nodes match {
    case node +: tail =>
      if (nodeHasAttributes(node, okButtonAttributes)) {
        findSimilarButtonRec(node.children ++ tail, okButtonAttributes, foundNodes :+ node)
      } else {
        findSimilarButtonRec(node.children ++ tail, okButtonAttributes, foundNodes)
      }

    case Seq() =>
      foundNodes
  }

  /**
    * Checks whether the node has any of the attributes passed by parameter.
    */
  private def nodeHasAttributes(node: NodeWithParents,
                                okButtonAttributes: Map[String, String]
                               ): Boolean = {
    node.attributes.exists{
      case (attributeKey, attributeValue) =>
        okButtonAttributes
          .get(attributeKey)
          .contains(attributeValue)
    }
  }
}

/**
  * As Scala XMl doesn't have the parent reference, we need to have that reference.
  */
class NodeWithParents(val node: Node,
                      val parents: Seq[String]) {

  def attributes: Map[String, String] =
    node.attributes.asAttrMap

  def children: Seq[NodeWithParents] = {
    val newParents: Seq[String] =
      parents :+ node.label

    node.child.map(child =>
      new NodeWithParents(child, newParents)
    )
  }
}
