package aggregateservice.scala.actions

import scala.util.control.Exception.allCatch
import aggregateservice.custom.exceptions._
import aggregateservice.custom.exceptions.NumberTooLargeException
import aggregateservice.custom.exceptions.NumberTooSmallException


class AggregateActionDouble extends AggregateAction {


   val aggregateSum: AggregateSum = new AggregateSum

  @throws(classOf[Exception])
  def sum(values: String): Double =
    {
      println(s"String for which sum needs to be calculated [" + values + "].")
      var numbers: Array[String] = null
      try {
        numbers = values.split(", ")
        aggregateSum.doSumDoubleValues(numbers)._1
      } catch {
        case numberFormatException: NumberFormatException => {
          println(numberFormatException.printStackTrace())
          throw numberFormatException
        }
        case numberTooLargeException: NumberTooLargeException => {
          println(numberTooLargeException.printStackTrace())
          throw numberTooLargeException
        }
        case numberTooSmallException: NumberTooSmallException => {
          println(numberTooSmallException.printStackTrace())
          throw numberTooSmallException
        }
        case exception: Exception => {
          println(exception.printStackTrace())
          throw new Exception("Exception occured while parsing string [" + values + "].")
        }
      }
    }

  @throws(classOf[Exception])
  def mean(values: String): Double =
    {
      println(s"String for which mean needs to be calculated [" + values + "].")
      var numbers: Array[String] = null
      var mean: Double = 0.0
      try {
        numbers = values.split(", ")
        val sumDetails = aggregateSum.doSumDoubleValues(numbers)
        mean = sumDetails._1 / sumDetails._2
        println(s"Mean for [" + values + "] is" + mean + ".")
        mean
      } catch {
        case numberFormatException: NumberFormatException => {
          println(numberFormatException.printStackTrace())
          throw numberFormatException
        }
        case numberTooLargeException: NumberTooLargeException => {
          println(numberTooLargeException.printStackTrace())
          throw numberTooLargeException
        }
        case numberTooSmallException: NumberTooSmallException => {
          println(numberTooSmallException.printStackTrace())
          throw numberTooSmallException
        }
        case arithmeticException: ArithmeticException => {
          println(arithmeticException.printStackTrace())
          throw new ArithmeticException("Exception occured while computing mean.")
        }
        case exception: Exception => {
          println(exception.printStackTrace())
          throw new Exception("Exception occured while parsing string [" + values + "].")
        }
      }
    }

  @throws(classOf[Exception])
  def max(values: String): Double =
    {
      println(s"String for which max needs to be found [" + values + "].")
      var numbers: Array[String] = null
      var max: Double = Double.MinValue
      var num: String = ""
      try {
        numbers = values.split(", ")

        for (n <- numbers) {
          if (n.trim.endsWith("d") || n.trim.endsWith("D")) {
            num = n.trim().dropRight(1)
          } else {
            num = n
          }
          

          if (BigDecimal(num) > BigDecimal(Double.MaxValue)) throw new NumberTooLargeException(num + ": value is too large for double data type.")
          else if (BigDecimal(num) < BigDecimal(Double.MinValue)) throw new NumberTooSmallException(num + ": value is too small for double data type.")

          var tempNumber = n.toDouble
          if (tempNumber > max) {
            max = tempNumber
          }
        }
        println(s"Max for [" + values + "] is" + max + ".")
        max
      } catch {
        case numberFormatException: NumberFormatException => {
          println(numberFormatException.printStackTrace())
          throw new NumberFormatException("String ["+ num +"] cannot be parsed to double.")
        }
        case numberTooLargeException: NumberTooLargeException => {
          println(numberTooLargeException.printStackTrace())
          throw numberTooLargeException
        }
        case numberTooSmallException: NumberTooSmallException => {
          println(numberTooSmallException.printStackTrace())
          throw numberTooSmallException
        }
        case exception: Exception => {
          println(exception.printStackTrace())
          throw new Exception("Exception occured while parsing string [" + values + "].")
        }
      }
    }

}