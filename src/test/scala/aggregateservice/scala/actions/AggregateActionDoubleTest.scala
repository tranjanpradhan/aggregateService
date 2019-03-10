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
    assert(aggregateActionDouble.sum("12.0") == 12.0)
  }

  test("""sum function for double should return the sum of three elements present in string separated by a " ,"""") {
    assert(aggregateActionDouble.sum("12.0, 13.00, 20.00") == 45.0)
  }

  test("sum function for double should return an instance of Double") {
    assert(aggregateActionDouble.sum("12.0, 13.00, 20.00").isInstanceOf[Double])
  }

  test("sum function for double should identify the negative numbers and compute the sum") {
    assert(aggregateActionDouble.sum("12.0, -13.00, 20.00") == 19.0)
  }

  test("sum function for double should add fractions") {
    assert(aggregateActionDouble.sum("2.5, -1.05, 123.96") == 125.41)
  }

  test("sum function for double should identify double and compute sum of Double type") {
    assert(aggregateActionDouble.sum("2, 3, 5") == 10.0 &&
      aggregateActionDouble.sum("2, 3, 5").isInstanceOf[Double])
  }

  test("sum function for double should return max value of double when one element is the max value of double") {
    assert(aggregateActionDouble.sum(Double.MaxValue.toString() + ", 3, 5") == Double.MaxValue)
  }

  test("sum function for double should return posetive value of infinity when one element is the posetive value of infinity") {
    assert(aggregateActionDouble.sum(Double.PositiveInfinity + ", 3, 5") == Double.PositiveInfinity)
  }

  test("sum function for double should return negative value of infinity when one element is the negative value of infinity") {
    assert(aggregateActionDouble.sum(Double.NegativeInfinity + ", 3, 5") == Double.NegativeInfinity)
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

}