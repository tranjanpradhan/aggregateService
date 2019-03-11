package aggregateservice.scala.actions

import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import org.scalactic.source.Position.apply
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class AggregateActionBigDecimalTest extends FunSuite with BeforeAndAfter {

  var aggregateActionBigDecimal: AggregateActionBigDecimal = _

  before {
    aggregateActionBigDecimal = new AggregateActionBigDecimal()
  }

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
    val sum:BigDecimal = aggregateActionBigDecimal.sum(BigDecimal(Double.MaxValue).toString+", "+BigDecimal(Double.MaxValue).toString)
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

  /*test("sum function for long should return max value of long when one element is the max value of long") {
    val sum = aggregateActionLong.sum(Long.MaxValue.toString() + ", 3, 5")
    assert(sum.isInstanceOf[Long] & sum == Long.MaxValue)
  } (NEED TO CHECK)*/

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


}