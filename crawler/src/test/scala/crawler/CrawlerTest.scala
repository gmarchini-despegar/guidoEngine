package crawler

import crawler.parsing.OkButtonParser
import org.scalatest.{FlatSpec, Matchers}

class CrawlerTest
  extends FlatSpec
    with Matchers {

  it should "not find the ok button" in {
    // arrange
    val okButtonParser: OkButtonParser = new OkButtonParser()
    val crawler: Crawler = new Crawler(okButtonParser)

    // act / assert
    a[RuntimeException] shouldBe thrownBy(
      crawler.findOkButton("/html-original.html", "/html-noOkButton.html")
    )
  }

  it should "find the ok button" in {
    // arrange
    val okButtonParser: OkButtonParser = new OkButtonParser()
    val crawler: Crawler = new Crawler(okButtonParser)

    // act
    val result: String =
      crawler.findOkButton("/html-original.html", "/html-case_1.html")

    // assert
    result shouldEqual "html > body > div > div > div > div > div > div"
  }

  it should "find the ok button - case 2" in {
    // arrange
    val okButtonParser: OkButtonParser = new OkButtonParser()
    val crawler: Crawler = new Crawler(okButtonParser)

    // act
    val result: String =
      crawler.findOkButton("/html-original.html", "/html-case_2.html")

    // assert
    result shouldEqual "html > body > div > div > div > div > div > div > div"
  }

  it should "find the ok button - case 3" in {
    // arrange
    val okButtonParser: OkButtonParser = new OkButtonParser()
    val crawler: Crawler = new Crawler(okButtonParser)

    // act
    val result: String =
      crawler.findOkButton("/html-original.html", "/html-case_3.html")

    // assert
    result shouldEqual "html > body > div > div > div > div > div > div"
  }

  it should "find the ok button - case 4" in {
    // arrange
    val okButtonParser: OkButtonParser = new OkButtonParser()
    val crawler: Crawler = new Crawler(okButtonParser)

    // act
    val result: String =
      crawler.findOkButton("/html-original.html", "/html-case_4.html")

    // assert
    result shouldEqual "html > body > div > div > div > div > div > div"
  }
}
