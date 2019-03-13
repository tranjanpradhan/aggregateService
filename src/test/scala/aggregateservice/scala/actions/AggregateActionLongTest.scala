package aggregateservice.scala.actions

import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import org.scalactic.source.Position.apply
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import aggregateservice.custom.exceptions._
import aggregateservice.custom.exceptions.NumberTooLargeException
import aggregateservice.custom.exceptions.NumberTooSmallException

@RunWith(classOf[JUnitRunner])
class aggregateActionLongTest extends FunSuite with BeforeAndAfter {

  var aggregateActionLong: AggregateActionLong = _

  before {
    aggregateActionLong = new AggregateActionLong()
  }

  test("sum function for long should return the string itself if it has only one element") {
    val sum = aggregateActionLong.sum("12")
    assert(sum.isInstanceOf[Long] & sum == 12)
  }

  test("sum function for long should identify the negative numbers and compute the sum") {
    val sum = aggregateActionLong.sum("12, -13, 20")
    assert(sum.isInstanceOf[Long] & sum == 19)
  }

  test("sum function for long should identify the string ending with \"l\" and compute sum of Long type") {
    val sum = aggregateActionLong.sum("7l, 12l, 5")
    assert(sum == 24 & sum.isInstanceOf[Long])
  }

  test("sum function for long should identify the string ending with \"L\" and compute sum of Long type") {
    val sum = aggregateActionLong.sum("7L, 12L, 5")
    assert(sum == 24 & sum.isInstanceOf[Long])
  }

  /*test("sum function for long should return max value of long when one element is the max value of long") {
    val sum = aggregateActionLong.sum(Long.MaxValue.toString() + ", 3, 5")
    assert(sum.isInstanceOf[Long] & sum == Long.MaxValue)
  } (NEED TO CHECK)*/

  test("sum function for long should throw exception when strings have no numbers") {
    val thrown = intercept[NumberFormatException] {
      aggregateActionLong.sum("test1, test2, test3")
    }
    assert(thrown.getMessage === "String [test1] cannot be parsed to long.")
  }

  test("sum function for long should throw exception when strings contains fractions") {
    val thrown = intercept[NumberFormatException] {
      aggregateActionLong.sum("2.2, 3.4")
    }
    assert(thrown.getMessage === "String [2.2] cannot be parsed to long.")
  }

  test("sum function for long should throw exception when passed empty strings") {
    val thrown = intercept[Exception] {
      aggregateActionLong.sum("")
    }
    assert(thrown.getMessage === "String [] cannot be parsed to long.")
  }

  test("sum function for long should throw exception when numbers are greater than max value of long") {
    val thrown = intercept[NumberTooLargeException] {
      aggregateActionLong.sum(BigInt("19223372036854775808") + ", 2, 5")
    }
    assert(thrown.getMessage === BigInt("19223372036854775808") + ": value is too large for long data type.")
  }

  test("sum function for long should throw exception when numbers are lesser than min value of long") {
    val thrown = intercept[NumberTooSmallException] {
      aggregateActionLong.sum(BigInt("-92233720368547758098") + ", 2, 5")
    }
    assert(thrown.getMessage === BigInt("-92233720368547758098") + ": value is too small for long data type.")
  }

  test("sum function for long should throw exception if the string is not ending with \"l\" or \"L\"") {
    val thrown = intercept[Exception] {
      val sum = aggregateActionLong.sum("7.5f, 14.5F, 5")
    }
    assert(thrown.getMessage === "String [7.5f] cannot be parsed to long.")
  }

  //Unit test cases for mean function

  test("mean function for long should return the string itself if it has only one element") {
    val mean = aggregateActionLong.mean("12")
    assert(mean.isInstanceOf[Long] & mean == 12)
  }

  test("mean function for long should identify the negative numbers and compute the mean") {
    val mean = aggregateActionLong.mean("12, -13, 20")
    assert(mean.isInstanceOf[Long] & mean == 6)
  }

  test("mean function for long should identify the string ending with \"l\" and compute mean of Long type") {
    val mean = aggregateActionLong.mean("7l, 12l, 5")
    assert(mean == 8 & mean.isInstanceOf[Long])
  }

  test("mean function for long should identify the string ending with \"L\" and compute mean of Long type") {
    val mean = aggregateActionLong.mean("7L, 12L, 5")
    assert(mean == 8 & mean.isInstanceOf[Long])
  }

  test("mean function for long should throw exception when strings have no numbers") {
    val thrown = intercept[NumberFormatException] {
      aggregateActionLong.mean("test1, test2, test3")
    }
    assert(thrown.getMessage === "String [test1] cannot be parsed to long.")
  }

  test("mean function for long should throw exception when strings contains fractions") {
    val thrown = intercept[NumberFormatException] {
      aggregateActionLong.mean("2.2, 3.4")
    }
    assert(thrown.getMessage === "String [2.2] cannot be parsed to long.")
  }

  test("mean function for long should throw exception when passed empty strings") {
    val thrown = intercept[Exception] {
      aggregateActionLong.mean("")
    }
    assert(thrown.getMessage === "String [] cannot be parsed to long.")
  }

  test("mean function for long should throw exception when numbers are greater than max value of long") {
    val thrown = intercept[NumberTooLargeException] {
      aggregateActionLong.mean(BigInt("19223372036854775808") + ", 2, 5")
    }
    assert(thrown.getMessage === BigInt("19223372036854775808") + ": value is too large for long data type.")
  }

  test("mean function for long should throw exception when numbers are lesser than min value of long") {
    val thrown = intercept[NumberTooSmallException] {
      aggregateActionLong.mean(BigInt("-92233720368547758098") + ", 2, 5")
    }
    assert(thrown.getMessage === BigInt("-92233720368547758098") + ": value is too small for long data type.")
  }

  test("mean function for long should throw exception if the string is not ending with \"l\" or \"L\"") {
    val thrown = intercept[Exception] {
      val sum = aggregateActionLong.mean("7.5f, 14.5F, 5")
    }
    assert(thrown.getMessage === "String [7.5f] cannot be parsed to long.")
  }

}