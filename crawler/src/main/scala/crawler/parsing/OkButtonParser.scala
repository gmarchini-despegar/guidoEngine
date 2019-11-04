package crawler.parsing

import scala.annotation.tailrec
import scala.xml.{Elem, Node, XML}

class OkButtonParser {

  def findOkButton(html: String): Option[Node] = {
    val xml: Elem = XML.loadString(html)

    findOkButtonRec(xml)
  }

  @tailrec private def findOkButtonRec(nodes: Seq[Node]): Option[Node] = nodes match {
    case node +: tail =>
      if (isOkButton(node)) {
        Some(node)
      } else {
        findOkButtonRec(node.child ++ tail)
      }

    case Seq() =>
      None
  }

  private def isOkButton(node: Node) = {
    (node \ "@id").text == "make-everything-ok-button"
  }
}
