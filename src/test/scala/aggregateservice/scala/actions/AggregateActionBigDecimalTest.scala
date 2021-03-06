package aggregateservice.scala.actions

import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import org.scalactic.source.Position.apply
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import aggregateservice.custom.exceptions._

@RunWith(classOf[JUnitRunner])
class AggregateActionBigDecimalTest extends FunSuite with BeforeAndAfter {

  var aggregateActionBigDecimal: AggregateActionBigDecimal = _

  before {
    aggregateActionBigDecimal = new AggregateActionBigDecimal()
  }

  //Unit test cases for sum

  test("sum function for bigdecimal should return the string itself if it has only one element") {
    val sum = aggregateActionBigDecimal.sum("12.65")
    assert(sum.isInstanceOf[BigDecimal] & sum == 12.65)
  }

  test("sum function for bigdecimal should identify the negative numbers and compute the sum") {
    val sum = aggregateActionBigDecimal.sum("12, -13, 20")
    assert(sum.isInstanceOf[BigDecimal] & sum == 19)
  }

  test("sum function for bigdecimal should add fractions") {
    val sum = aggregateActionBigDecimal.sum("2.5, -1.05, 123.96")
    assert(sum.isInstanceOf[BigDecimal] & sum == 125.41)
  }

  test("sum function for bigdecimal should be able to add numbers greater than double max value") {
    val sum: BigDecimal = aggregateActionBigDecimal.sum(BigDecimal(Double.MaxValue).toString + ", " + BigDecimal(Double.MaxValue).toString)
    assert(sum.isInstanceOf[BigDecimal] & sum == BigDecimal("3.5953862697246314E+308"))
  }

  test("sum function for bigdecimal should identify the string ending with \"d\" and compute sum of bigdecimal type") {
    val sum = aggregateActionBigDecimal.sum("7d, 12d, 5")
    assert(sum == 24 & sum.isInstanceOf[BigDecimal])
  }

  test("sum function for bigdecimal should identify the string ending with \"D\" and compute sum of bigdecimal type") {
    val sum = aggregateActionBigDecimal.sum("7D, 12D, 5")
    assert(sum == 24 & sum.isInstanceOf[BigDecimal])
  }

  test("sum function for bigdecimal should throw exception when strings have no numbers") {
    val thrown = intercept[NumberFormatException] {
      aggregateActionBigDecimal.sum("test1, test2, test3")
    }
    assert(thrown.getMessage === "String [test1] cannot be parsed to BigDecimal.")
  }

  test("sum function for bigdecimal should throw exception when passed empty strings") {
    val thrown = intercept[Exception] {
      aggregateActionBigDecimal.sum("")
    }
    assert(thrown.getMessage === "String [] cannot be parsed to BigDecimal.")
  }

  test("sum function for bigdecimal should throw exception if the string is not ending with \"d\" or \"D\"") {
    val thrown = intercept[Exception] {
      val sum = aggregateActionBigDecimal.sum("7.5f, 14.5F, 5")
    }
    assert(thrown.getMessage === "String [7.5f] cannot be parsed to BigDecimal.")
  }

  //Unit test cases for mean

  test("mean function for bigdecimal should return the string itself if it has only one element") {
    val mean = aggregateActionBigDecimal.mean("12.65")
    assert(mean.isInstanceOf[BigDecimal] & mean == 12.65)
  }

  test("mean function for bigdecimal should identify the negative numbers and compute the mean") {
    val mean = aggregateActionBigDecimal.mean("12, -13, 20")
    assert(mean.isInstanceOf[BigDecimal] & mean == (BigDecimal(12) + BigDecimal(-13) + BigDecimal(20)) / 3)
  }

  test("mean function for bigdecimal should compute mean for fractions") {
    val mean = aggregateActionBigDecimal.mean("2.5, -1.05, 123.96")
    assert(mean.isInstanceOf[BigDecimal] & mean == (BigDecimal(2.5) + BigDecimal(-1.05) + BigDecimal(123.96)) / 3)
  }

  test("mean function for bigdecimal should be able to compute mean greater than double max value") {
    val mean = aggregateActionBigDecimal.mean(BigDecimal(Double.MaxValue).toString + ", " + BigDecimal(Double.MaxValue).toString)
    assert(mean.isInstanceOf[BigDecimal] & mean == BigDecimal("1.7976931348623157E+308"))
  }

  test("mean function for bigdecimal should identify the string ending with \"d\" and compute mean of bigdecimal type") {
    val mean = aggregateActionBigDecimal.mean("7d, 12d, 5")
    assert(mean == 8.0 & mean.isInstanceOf[BigDecimal])
  }

  test("mean function for bigdecimal should identify the string ending with \"D\" and compute mean of bigdecimal type") {
    val mean = aggregateActionBigDecimal.mean("7D, 12D, 5")
    assert(mean == 8.0 & mean.isInstanceOf[BigDecimal])
  }

  test("mean function for bigdecimal should throw exception when strings have no numbers") {
    val thrown = intercept[NumberFormatException] {
      aggregateActionBigDecimal.mean("test1, test2, test3")
    }
    assert(thrown.getMessage === "String [test1] cannot be parsed to BigDecimal.")
  }

  test("mean function for bigdecimal should throw exception when passed empty strings") {
    val thrown = intercept[Exception] {
      aggregateActionBigDecimal.mean("")
    }
    assert(thrown.getMessage === "String [] cannot be parsed to BigDecimal.")
  }

  test("mean function for bigdecimal should throw exception if the string is not ending with \"d\" or \"D\"") {
    val thrown = intercept[Exception] {
      val mean = aggregateActionBigDecimal.mean("7.5f, 14.5F, 5")
    }
    assert(thrown.getMessage === "String [7.5f] cannot be parsed to BigDecimal.")
  }

  //Unit test cases for max function
  test("max function for big decimal should return the string itself if it has only one element") {
    val max = aggregateActionBigDecimal.max("12D")
    assert(max.isInstanceOf[BigDecimal] & max == 12.0)
  }

  test("max function for big decimal should identify the negative numbers and compute the max") {
    val max = aggregateActionBigDecimal.max("12, -13d, 20D")
    assert(max.isInstanceOf[BigDecimal] & max == 20)
  }

  test("max function for big decimal should identify the max value when some numbers are repeated") {
    val max = aggregateActionBigDecimal.max("2d, -1, -123.54, 2")
    assert(max.isInstanceOf[BigDecimal] & max == 2)
  }

  test("max function for big decimal should identify the string ending with \"d\" and compute max of big decimal type") {
    val max = aggregateActionBigDecimal.max("7d, 123.89d, 5")
    assert(max == 123.89 & max.isInstanceOf[BigDecimal])
  }

  test("max function for big decimal should identify the string ending with \"D\" and compute max of big decimal type") {
    val max = aggregateActionBigDecimal.max("7D, -123.87D, 5")
    assert(max == 7 & max.isInstanceOf[BigDecimal])
  }

  test("max function for big decimal should throw exception when strings have no numbers") {
    val thrown = intercept[NumberFormatException] {
      aggregateActionBigDecimal.max("test1, test2, test3")
    }
    assert(thrown.getMessage === "String [test1] cannot be parsed to big decimal.")
  }

  test("max function for big decimal should throw exception when passed empty strings") {
    val thrown = intercept[Exception] {
      aggregateActionBigDecimal.max("")
    }
    assert(thrown.getMessage === "String [] cannot be parsed to big decimal.")
  }

  test("max function for bigdecimal should be able to compute max when greater than double max value") {
    val max = aggregateActionBigDecimal.max(BigDecimal(Double.MaxValue).toString + ", " + BigDecimal(Double.MaxValue).toString)
    assert(max.isInstanceOf[BigDecimal] & max == BigDecimal("1.7976931348623157E+308"))
  }
}