package crawler

import crawler.parsing.NodeParser
import utils.FileUtils

object Main
  extends App {

  /**
    * Used to run the jar. First argument should be the original file path, and the second one should be the new html path.
    */
  val crawler =
    new Crawler(
      nodeParser = new NodeParser(),
      fileUtils = FileUtils.externalFileUtils
    )

  args.length match {
    case 2 =>
      println(crawler.findOkButton(args(0), args(1)))

    case 3 =>
      println(crawler.findOkButtonByOriginalId(args(0), args(1), args(2)))

    case _ =>
      throw new IllegalArgumentException("Invalid arguments. They should be: \n1) original_html, new_html\nOR\n2) original_html, new_html, ok_button_id")
  }
}
