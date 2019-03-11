package aggregateservice.scala.actions

import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import org.scalactic.source.Position.apply
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class AggregateActionDoubleTest extends FunSuite with BeforeAndAfter {

  var aggregateActionDouble: AggregateActionDouble = _

  before {
    aggregateActionDouble = new AggregateActionDouble()
  }

  test("sum function for double should return the string itself if it has only one element") {
    val sum = aggregateActionDouble.sum("12.0")
    assert(sum.isInstanceOf[Double] & sum == 12.0)
  }

  test("sum function for double should identify the negative numbers and compute the sum") {
    val sum = aggregateActionDouble.sum("12.0, -13.00, 20.00")
    assert(sum.isInstanceOf[Double] & sum == 19.0)
  }

  test("sum function for double should add fractions") {
    val sum = aggregateActionDouble.sum("2.5, -1.05, 123.96")
    assert(sum.isInstanceOf[Double] & sum == 125.41)
  }

  test("sum function for double should identify double and compute sum of Double type") {
    val sum = aggregateActionDouble.sum("2, 3, 5")
    assert(sum == 10.0 & sum.isInstanceOf[Double])
  }

  test("sum function for double should identify the string ending with \"d\" and compute sum of Double type") {
    val sum = aggregateActionDouble.sum("7.8d, 123, 5")
    assert(sum == 135.8 & sum.isInstanceOf[Double])
  }

  test("sum function for double should identify the string ending with \"D\" and compute sum of Double type") {
    val sum = aggregateActionDouble.sum("7.8D, 123, 5")
    assert(sum == 135.8 & sum.isInstanceOf[Double])
  }

  test("sum function for double should return max value of double when one element is the max value of double") {
    val sum = aggregateActionDouble.sum(Double.MaxValue.toString() + ", 3, 5")
    assert(sum.isInstanceOf[Double] & sum == Double.MaxValue)
  }

  test("sum function for double should throw exception when strings have no numbers") {
    val thrown = intercept[NumberFormatException] {
      aggregateActionDouble.sum("test1, test2, test3")
    }
    assert(thrown.getMessage === "String [test1] cannot be parsed to double.")
  }

  test("sum function for double should throw exception when passed empty strings") {
    val thrown = intercept[Exception] {
      aggregateActionDouble.sum("")
    }
    assert(thrown.getMessage === "String [] cannot be parsed to double.")
  }

  test("sum function for double should throw exception when numbers are greater than max value of double") {
    val thrown = intercept[NumberTooLargeException] {
      aggregateActionDouble.sum(BigDecimal("2.7976931348623157E+308") + ", 2.0, 5.9")
    }
    assert(thrown.getMessage === BigDecimal("2.7976931348623157E+308") + ": value is too large for double data type.")
  }

  test("sum function for double should throw exception when numbers are lesser than min value of double") {
    val thrown = intercept[NumberTooSmallException] {
      aggregateActionDouble.sum(BigDecimal("-21.7976931348623157E+308") + ", 0.0, 5.9")
    }
    assert(thrown.getMessage === BigDecimal("-21.7976931348623157E+308") + ": value is too small for double data type.")
  }
  
   test("sum function for double should throw exception if the string is not ending with \"d\" or \"D\"") {
    val thrown = intercept[Exception] {
      val sum = aggregateActionDouble.sum("7.5f, 14.5F, 5")
    }
    assert(thrown.getMessage === "String [7.5f] cannot be parsed to double.")
  }

}