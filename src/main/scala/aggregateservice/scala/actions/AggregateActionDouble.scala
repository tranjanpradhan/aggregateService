package aggregateservice.scala.actions

import scala.util.control.Exception.allCatch
import org.apache.log4j.Logger

class AggregateActionDouble extends AggregateAction {

  @transient lazy val logger = Logger.getLogger(getClass.getName)
  val aggregateSum: AggregateSum = new AggregateSum

  @throws(classOf[Exception])
  def sum(values: String): Double =
    {
      logger.info(s"String for which sum needs to be calculated [" + values + "].")
      var numbers: Array[String] = null
      try {
        numbers = values.split(", ")
        aggregateSum.doSumDoubleValues(numbers)._1
      } catch {
        case numberFormatException: NumberFormatException => {
          logger.error(numberFormatException.printStackTrace())
          throw numberFormatException
        }
        case numberTooLargeException: NumberTooLargeException => {
          logger.error(numberTooLargeException.printStackTrace())
          throw numberTooLargeException
        }
        case numberTooSmallException: NumberTooSmallException => {
          logger.error(numberTooSmallException.printStackTrace())
          throw numberTooSmallException
        }
        case exception: Exception => {
          logger.error(exception.printStackTrace())
          throw new Exception("Exception occured while parsing string [" + values + "].")
        }
      }
    }

  @throws(classOf[Exception])
  def mean(values: String): Double =
    {
      logger.info(s"String for which mean needs to be calculated [" + values + "].")
      var numbers: Array[String] = null
      var mean: Double = 0.0
      try {
        numbers = values.split(", ")
        val sumDetails = aggregateSum.doSumDoubleValues(numbers)
        mean = sumDetails._1 / sumDetails._2
        logger.info(s"Mean for [" + values + "] is" + mean + ".")
        mean
      } catch {
        case numberFormatException: NumberFormatException => {
          logger.error(numberFormatException.printStackTrace())
          throw numberFormatException
        }
        case numberTooLargeException: NumberTooLargeException => {
          logger.error(numberTooLargeException.printStackTrace())
          throw numberTooLargeException
        }
        case numberTooSmallException: NumberTooSmallException => {
          logger.error(numberTooSmallException.printStackTrace())
          throw numberTooSmallException
        }
        case arithmeticException: ArithmeticException => {
          logger.error(arithmeticException.printStackTrace())
          throw new ArithmeticException("Exception occured while computing mean.")
        }
        case exception: Exception => {
          logger.error(exception.printStackTrace())
          throw new Exception("Exception occured while parsing string [" + values + "].")
        }
      }
    }

  @throws(classOf[Exception])
  def max(values: String): Double =
    {
      logger.info(s"String for which max needs to be found [" + values + "].")
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
          var tempNumber = n.toDouble

          if (BigDecimal(tempNumber) > BigDecimal(Double.MaxValue)) throw new NumberTooLargeException(tempNumber + ": value is too large for double data type.")
          else if (BigDecimal(tempNumber) < BigDecimal(Double.MinValue)) throw new NumberTooSmallException(tempNumber + ": value is too small for double data type.")

          if (tempNumber > max) {
            max = tempNumber
          }
        }
        logger.info(s"Max for [" + values + "] is" + max + ".")
        max
      } catch {
        case numberFormatException: NumberFormatException => {
          logger.error(numberFormatException.printStackTrace())
          throw numberFormatException
        }
        case numberTooLargeException: NumberTooLargeException => {
          logger.error(numberTooLargeException.printStackTrace())
          throw numberTooLargeException
        }
        case numberTooSmallException: NumberTooSmallException => {
          logger.error(numberTooSmallException.printStackTrace())
          throw numberTooSmallException
        }
        case exception: Exception => {
          logger.error(exception.printStackTrace())
          throw new Exception("Exception occured while parsing string [" + values + "].")
        }
      }
    }

}