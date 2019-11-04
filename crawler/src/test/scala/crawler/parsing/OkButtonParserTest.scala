package crawler.parsing

import org.scalatest.{FlatSpec, Matchers}
import utils.FileUtils

class OkButtonParserTest
  extends FlatSpec
    with Matchers {

  "find the ok button" should behave like {
    it should "find the OK button" in {
      // arrange
      val fileAsString: String = FileUtils.getResourceFileAsString("/html-original.html")

      // act
      val result = new OkButtonParser().findOkButton(fileAsString)

      // assert
      result shouldBe 'defined
    }

    it should "not find the OK button" in {
      // arrange
      val fileAsString: String = FileUtils.getResourceFileAsString("/html-noOkButton.html")

      // act
      val result = new OkButtonParser().findOkButton(fileAsString)

      // assert
      result shouldBe 'empty
    }
  }
}
