package aggregateservice.scala.actions

import scala.util.control.Exception.allCatch


class AggregateActionBigDecimal extends AggregateAction {


  val aggregateSum: AggregateSum = new AggregateSum

  @throws(classOf[Exception])
  def sum(values: String): BigDecimal =
    {
      println(s"String for which sum needs to be calculated [" + values + "].")
      var numbers: Array[String] = null
      try {
        numbers = values.split(", ")
        aggregateSum.doSumBigDecimalValues(numbers)._1
      } catch {
        case numberFormatException: NumberFormatException => {
          println(numberFormatException.printStackTrace())
          throw numberFormatException
        }
        case exception: Exception => {
          println(exception.printStackTrace())
          throw new Exception("Exception occured while parsing string [" + values + "].")
        }
      }
    }

  @throws(classOf[Exception])
  def mean(values: String): BigDecimal =
    {
      println(s"String for which mean needs to be calculated [" + values + "].")
      var numbers: Array[String] = null
      var mean: BigDecimal = 0.0d
      try {
        numbers = values.split(", ")
        val sumDetails = aggregateSum.doSumBigDecimalValues(numbers)
        mean = sumDetails._1 / BigDecimal(sumDetails._2)
        println(s"Mean for [" + values + "] is" + mean + ".")
        mean
      } catch {
        case numberFormatException: NumberFormatException => {
          println(numberFormatException.printStackTrace())
          throw numberFormatException
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
  def max(values: String): BigDecimal =
    {
      println(s"String for which max needs to be found [" + values + "].")
      var numbers: Array[String] = null
      var max: BigDecimal = 0.0d
      var num: String = ""
      var index = 0
      try {
        numbers = values.split(", ")
        for (n <- numbers) {
          if (n.trim.endsWith("d") || n.trim.endsWith("D")) {
            num = n.trim().dropRight(1)
          } else {
            num = n.trim()
          }
          var tempNumber = BigDecimal(num)
          if (index == 0) max = tempNumber
          if (tempNumber > max) {
            max = tempNumber
          }
          index = index + 1
        }
        println(s"Max for [" + values + "] is " + max + ".")
        max
      } catch {
         case numberFormatException: NumberFormatException => {
          println(numberFormatException.printStackTrace())
          throw new NumberFormatException("String ["+ num +"] cannot be parsed to big decimal.")
        }
        case exception: Exception => {
          println(exception.printStackTrace())
          throw new Exception("Exception occured while parsing string [" + values + "].")
        }
      }
    }

}