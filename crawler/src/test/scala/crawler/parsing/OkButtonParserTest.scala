package crawler.parsing

import crawler.utils.FileUtils
import org.scalatest.{FlatSpec, Matchers}

class OkButtonParserTest
  extends FlatSpec
    with Matchers {

  it should "find the OK button" in {
    val fileAsString: String = FileUtils.getResourceFileAsString("/html1.xml")

    new OkButtonParser().findOkButton(fileAsString) shouldBe 'defined
  }

  it should "not find the OK button" in {
    val fileAsString: String = FileUtils.getResourceFileAsString("/html-noOkButton.xml")

    new OkButtonParser().findOkButton(fileAsString) shouldBe 'empty
  }
}
