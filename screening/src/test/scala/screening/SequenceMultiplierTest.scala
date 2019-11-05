package screening

import org.scalatest.{FlatSpec, Matchers}

class SequenceMultiplierTest
  extends FlatSpec
    with Matchers {

  val sequenceMultiplier: SequenceMultiplier =
    new SequenceMultiplier()

  "recruit assumptions" should behave like {
    "given an empty sequence" should "return an empty sequence" in {
      // arrange
      val toProcess: Seq[Int] =
        Seq()

      // act
      val result: Seq[Int] =
        sequenceMultiplier.multiplyEachOtherNumber(toProcess)

      // assert
      result shouldBe 'empty
    }

    "given a single element sequence" should "return a sequence with 1" in {
      // arrange
      val toProcess: Seq[Int] =
        Seq(10)

      // act
      val result: Seq[Int] =
        sequenceMultiplier.multiplyEachOtherNumber(toProcess)

      // assert
      result shouldEqual Seq(1)
    }
  }

  // happy path
  "given the Seq(1, 2, 3, 4)" should "return the multiplied total without the index" in {
    // arrange
    val toProcess: Seq[Int] =
      Seq(1, 2, 3, 4)

    // act
    val result: Seq[Int] =
      sequenceMultiplier.multiplyEachOtherNumber(toProcess)

    // assert
    result shouldEqual Seq(24, 12, 8, 6)
  }

  "given a Seq with a single zero" should behave like {
    "zero as first element" should "return the multiples for first position" in {
      // arrange
      val toProcess: Seq[Int] =
        Seq(0, 2, 3, 4)

      // act
      val result: Seq[Int] =
        sequenceMultiplier.multiplyEachOtherNumber(toProcess)

      // assert
      result shouldEqual Seq(24, 0, 0, 0)
    }

    "zero as element in the middle" should "return the multiples only for the zero position" in {
      // arrange
      val toProcess: Seq[Int] =
        Seq(2, 3, 0, 4)

      // act
      val result: Seq[Int] =
        sequenceMultiplier.multiplyEachOtherNumber(toProcess)

      // assert
      result shouldEqual Seq(0, 0, 24, 0)
    }
  }

  "given a Seq with multiple zeroes" should behave like {
    "two zeroes" should "return a Seq with only zeroes" in {
      // arrange
      val toProcess: Seq[Int] =
        Seq(0, 2, 3, 0)

      // act
      val result: Seq[Int] =
        sequenceMultiplier.multiplyEachOtherNumber(toProcess)

      // assert
      result shouldEqual Seq(0, 0, 0, 0)
    }
  }




}
