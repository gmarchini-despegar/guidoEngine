package crawler.parsing

import scala.annotation.tailrec
import scala.xml.{Elem, Node, XML}

class NodeParser {

  /**
    * Finds the node that matches the given id.
    */
  def findNodeById(html: String,
                   nodeId: String
                  ): Option[NodeWithParents] = {
    val xml: Elem = XML.loadString(html)
    val xmlAsNodeWithParents = new NodeWithParents(xml, Seq())

    findSimilarButtonRec(
      nodes = Seq(xmlAsNodeWithParents),
      desiredAttributes = Map("id" -> nodeId)
    ).headOption
  }

  /**
    * given a Node, finds the first node that has any of its attributes.
    * This is a BFS function.
    */
  def findSimilarNodes(html: String,
                       nodeToSearch: NodeWithParents
                       ): Seq[NodeWithParents] = {
    val xml: Elem = XML.loadString(html)
    val xmlAsNodeWithParents = new NodeWithParents(xml, Seq())

    findSimilarButtonRec(
      nodes = Seq(xmlAsNodeWithParents),
      desiredAttributes = nodeToSearch.attributes
    )
  }

  /**
    * Finds a button that has any of the attributes passed by parameter.
    * If found, returns the NodeWithParents.
    */
  @tailrec private def findSimilarButtonRec(nodes: Seq[NodeWithParents],
                                            desiredAttributes: Map[String, String],
                                            foundNodes: Seq[NodeWithParents] = Seq()
                               ): Seq[NodeWithParents] = nodes match {
    case node +: tail =>
      if (nodeHasAttributes(node, desiredAttributes)) {
        findSimilarButtonRec(node.children ++ tail, desiredAttributes, foundNodes :+ node)
      } else {
        findSimilarButtonRec(node.children ++ tail, desiredAttributes, foundNodes)
      }

    case Seq() =>
      foundNodes
  }

  /**
    * Checks whether the node has any of the attributes passed by parameter.
    */
  private def nodeHasAttributes(node: NodeWithParents,
                                desiredAttributes: Map[String, String]
                               ): Boolean = {
    node.attributes.exists{
      case (attributeKey, attributeValue) =>
        desiredAttributes
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
