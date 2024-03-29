package crawler.parsing

import org.scalatest.{FlatSpec, Matchers}
import utils.FileUtilsTest

class NodeParserTest
  extends FlatSpec
    with FileUtilsTest
    with Matchers {

  "find the ok button" should behave like {
    it should "find the OK button" in {
      // arrange
      val fileAsString: String = fileUtils.getResourceFileAsString("html-original.html")

      // act
      val result = new NodeParser().findNodeById(fileAsString, "make-everything-ok-button")

      // assert
      result shouldBe 'defined
    }

    it should "find the tag by the given id" in {
      // arrange
      val fileAsString: String = fileUtils.getResourceFileAsString("html-original.html")

      // act
      val result = new NodeParser().findNodeById(fileAsString, "side-menu")

      // assert
      result shouldBe 'defined
    }

    it should "not find the OK button" in {
      // arrange
      val fileAsString: String = fileUtils.getResourceFileAsString("html-noOkButton.html")

      // act
      val result = new NodeParser().findNodeById(fileAsString, "make-everything-ok-button")

      // assert
      result shouldBe 'empty
    }
  }
}
