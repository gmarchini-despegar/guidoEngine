package screening

import scala.annotation.tailrec

class SequenceMultiplier {

  /**
    * Given a sequence of Integers, returns a sequence of the same size,
    * where each index is the product of every number, without the element of that index.
    * Ex:
    * Input: Seq[1, 2, 3, 4]
    * Output: Seq[2*3*4, 1*3*4, 1*2*4, 1*2*3]
    *
    * Runtime complexity:
    * Best case scenario: O(2N)
    * Worst case scenario (has a 0 as the first element): O(3N)
    */
  def multiplyEachOtherNumber(toProcess: Seq[Int]): Seq[Int] = {
    innerMultiply(
      toProcess  = toProcess,
      rightTotal = toProcess.product,
      leftTotal  = 1,
      result     = Seq()
    )
  }

  @tailrec private def innerMultiply(toProcess: Seq[Int],
                                     rightTotal: Int,
                                     leftTotal: Int,
                                     result: Seq[Int]
                                    ): Seq[Int] = toProcess match {

    // zero ruins everything. If there's a zero in the array, it means that product was zero.
    // that leaves us two options:
    // 1 - this is the only zero, so it can have a value
    // 2 - there are other zeroes, so the product of the tail should be zero
    case head +: tail if head == 0 => // oh zero, you ruined everything...
      val currentTotal: Int =
        leftTotal * tail.product

      (result :+ currentTotal) ++ tail.map(_ => 0)

    case currentNumber +: tail =>
      innerMultiply(
        toProcess = tail,
        rightTotal = rightTotal / currentNumber,
        leftTotal = leftTotal * currentNumber,
        result = result :+ (leftTotal * rightTotal / currentNumber)
      )

    case Seq() =>
      result
  }
}
