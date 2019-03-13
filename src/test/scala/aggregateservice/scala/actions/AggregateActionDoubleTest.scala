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
    val thrown = intercept[NumberFormatException] {
      val sum = aggregateActionDouble.sum("7.5f, 14.5F, 5")
    }
    assert(thrown.getMessage === "String [7.5f] cannot be parsed to double.")
  }

  //Unit test cases for mean function
  test("mean function for double should return the string itself if it has only one element") {
    val mean = aggregateActionDouble.mean("12.0")
    assert(mean.isInstanceOf[Double] & mean == 12.0)
  }

  test("mean function for double should identify the negative numbers and compute the mean") {
    val mean = aggregateActionDouble.mean("12.0, -13.00, 20.00")
    assert(mean.isInstanceOf[Double] & mean == 6.333333333333333)
  }

  test("mean function for double should compute mean for fractions") {
    val mean = aggregateActionDouble.mean("2.5, -1.05, 123.96")
    assert(mean.isInstanceOf[Double] & mean == 41.803333333333335)
  }

  test("mean function for double should identify double and compute sum of Double type") {
    val mean = aggregateActionDouble.mean("2, 3, 5")
    assert(mean == 3.3333333333333335 & mean.isInstanceOf[Double])
  }

  test("mean function for double should identify the string ending with \"d\" and compute mean of Double type") {
    val mean = aggregateActionDouble.mean("7.8d, 123, 5")
    assert(mean == 45.26666666666667 & mean.isInstanceOf[Double])
  }

  test("mean function for double should identify the string ending with \"D\" and compute mean of Double type") {
    val mean = aggregateActionDouble.mean("7.8D, 123, 5")
    assert(mean == 45.26666666666667 & mean.isInstanceOf[Double])
  }

  test("mean function for double should throw exception when strings have no numbers") {
    val thrown = intercept[NumberFormatException] {
      aggregateActionDouble.mean("test1, test2, test3")
    }
    assert(thrown.getMessage === "String [test1] cannot be parsed to double.")
  }

  test("mean function for double should throw exception when passed empty strings") {
    val thrown = intercept[Exception] {
      aggregateActionDouble.mean("")
    }
    assert(thrown.getMessage === "String [] cannot be parsed to double.")
  }

  test("mean function for double should throw exception when numbers are greater than max value of double") {
    val thrown = intercept[NumberTooLargeException] {
      aggregateActionDouble.mean(BigDecimal("2.7976931348623157E+308") + ", 2.0, 5.9")
    }
    assert(thrown.getMessage === BigDecimal("2.7976931348623157E+308") + ": value is too large for double data type.")
  }
  test("mean function for double should throw exception when numbers are lesser than min value of double") {
    val thrown = intercept[NumberTooSmallException] {
      aggregateActionDouble.mean(BigDecimal("-21.7976931348623157E+308") + ", 0.0, 5.9")
    }
    assert(thrown.getMessage === BigDecimal("-21.7976931348623157E+308") + ": value is too small for double data type.")
  }
  
  test("mean function for double should throw exception if the string is not ending with \"d\" or \"D\"") {
    val thrown = intercept[NumberFormatException] {
      val mean = aggregateActionDouble.mean("7.5f, 14.5F, 5")
    }
    assert(thrown.getMessage === "String [7.5f] cannot be parsed to double.")
  }
  
  //Unit test cases for max function
  test("max function for double should return the string itself if it has only one element") {
    val max = aggregateActionDouble.max("12.0")
    assert(max.isInstanceOf[Double] & max == 12.0)
  }
  
  test("max function for double should identify the negative numbers and compute the max") {
    val max = aggregateActionDouble.max("12.0, -13.00, 20.00")
    assert(max.isInstanceOf[Double] & max == 20.00)
  }
  
  test("max function for double should compute max for fractions") {
    val max = aggregateActionDouble.max("2.5, -1.05, 123.96")
    assert(max.isInstanceOf[Double] & max == 123.96)
  }
  
   test("max function for double should identify the max value when some numbers are repeated") {
    val max = aggregateActionDouble.max("2.0, -1.00, -123.96, 2.0")
    assert(max.isInstanceOf[Double] & max == 2.0)
  }
   
    test("max function for double should identify the string ending with \"d\" and compute max of Double type") {
    val max = aggregateActionDouble.max("7.8d, 123, 5")
    assert(max == 123 & max.isInstanceOf[Double])
  }

  test("max function for double should identify the string ending with \"D\" and compute max of Double type") {
    val max = aggregateActionDouble.max("7.8D, 123, 5")
    assert(max == 123 & max.isInstanceOf[Double])
  }

  test("max function for double should identify the max value of double") {
    val max = aggregateActionDouble.max(Double.MaxValue+", 123, 5")
    assert(max == Double.MaxValue & max.isInstanceOf[Double])
  }
    
  test("max function for double should return infinity") {
    val max = aggregateActionDouble.max(Double.MaxValue+", "+Double.PositiveInfinity+", 5")
    println(max)
    assert(max == Double.PositiveInfinity & max.isInstanceOf[Double])
  }

  test("max function for double should throw exception when strings have no numbers") {
    val thrown = intercept[NumberFormatException] {
      aggregateActionDouble.max("test1, test2, test3")
    }
    assert(thrown.getMessage === "String [test1] cannot be parsed to double.")
  }
  
 /* test("max function for double should throw exception when passed empty strings") {
    val thrown = intercept[Exception] {
      aggregateActionDouble.max("")
    }
    assert(thrown.getMessage === "String [] cannot be parsed to double.")
  }
  
  test("max function for double should throw exception when numbers are greater than max value of double") {
    val thrown = intercept[NumberTooLargeException] {
      aggregateActionDouble.max(BigDecimal("2.7976931348623157E+308") + ", 2.0, 5.9")
    }
    assert(thrown.getMessage === BigDecimal("2.7976931348623157E+308") + ": value is too large for double data type.")
  }
  
  test("max function for double should throw exception when numbers are lesser than min value of double") {
    val thrown = intercept[NumberTooSmallException] {
      aggregateActionDouble.max(BigDecimal("-21.7976931348623157E+308") + ", 0.0, 5.9")
    }
    assert(thrown.getMessage === BigDecimal("-21.7976931348623157E+308") + ": value is too small for double data type.")
  }
  
  test("max function for double should throw exception if the string is not ending with \"d\" or \"D\"") {
    val thrown = intercept[NumberFormatException] {
      val max = aggregateActionDouble.max("7.5f, 14.5F, 5")
    }
    assert(thrown.getMessage === "String [7.5f] cannot be parsed to double.")
  }*/
  
}