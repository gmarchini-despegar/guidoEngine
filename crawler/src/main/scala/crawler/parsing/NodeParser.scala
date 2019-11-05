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
    val xmlAsNodeWithParents = NodeWithParents(xml)

    findSimilarButtonRec(
      nodes = Seq(xmlAsNodeWithParents),
      desiredAttributes = Map("id" -> nodeId)
    ).headOption
  }

  /**
    * given a Node, finds the first node that has any of its attributes.
    * This is a BFS function.
    */
  def findNodeBySimilarity(html: String,
                           nodeToSearch: NodeWithParents
                          ): Seq[NodeWithParents] = {
    val xml: Elem = XML.loadString(html)
    val xmlAsNodeWithParents = NodeWithParents(xml)

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
      if (nodeMatchesAnyAttribute(node, desiredAttributes)) {
        findSimilarButtonRec(
          nodes = node.children ++ tail,
          desiredAttributes = desiredAttributes,
          foundNodes = foundNodes :+ node
        )
      } else {
        findSimilarButtonRec(
          nodes = node.children ++ tail,
          desiredAttributes = desiredAttributes,
          foundNodes = foundNodes
        )
      }

    case Seq() =>
      foundNodes
  }

  /**
    * Checks whether the node has any of the attributes passed by parameter.
    */
  private def nodeMatchesAnyAttribute(node: NodeWithParents,
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
                      val label: String,
                      val parents: Seq[String]) {

  def attributes: Map[String, String] =
    node.attributes.asAttrMap

  /**
    * Returns the node children as NodeWithParents.
    */
  def children: Seq[NodeWithParents] = {
    val newParents: Seq[String] =
      parents :+ label

    // we could just return the children, but we want to know the index of them,
    // in case we have multiple tags with the same name
    node
      .child
      .groupBy(_.label)
      .foldLeft(Seq[NodeWithParents]()) {
        case (result, (labelName, children)) if children.size == 1 => // just one children, don't need to pass the index
          result :+
            new NodeWithParents(
              node = children.head,
              label = labelName,
              parents = newParents
            )

        case (result, (labelName, children)) => // multiple children with same tag name
          result ++
          children.zipWithIndex.map {
            case (child, index) =>
              new NodeWithParents(
                node = child,
                label = s"$labelName[$index]",
                parents = newParents
              )
          }
      }
  }
}

object NodeWithParents {
  def apply(rootNode: Elem): NodeWithParents = {
    new NodeWithParents(
      node = rootNode,
      label = rootNode.label,
      parents = Seq()
    )
  }
}