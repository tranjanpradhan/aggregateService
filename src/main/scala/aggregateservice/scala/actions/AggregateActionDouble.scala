package aggregateservice.scala.actions

import scala.util.control.Exception.allCatch
import org.apache.log4j.Logger

class AggregateActionDouble extends AggregateAction {

  @transient lazy val logger = Logger.getLogger(getClass.getName)
  val aggregateSum: AggregateSum = new AggregateSum

  @throws(classOf[Exception])
  def sum(values: String): AnyVal =
    {
      logger.info(s"String for which sum needs to be calculated ["+values+"].")
      var numbers: Array[String] = null
      try {
        numbers = values.split(", ")
        aggregateSum.doSumDoubleValues(numbers)._1
      } catch {
        case numberFormatException: NumberFormatException  => {
          logger.error(numberFormatException.printStackTrace())
          throw numberFormatException
        }
        case numberTooLargeException: NumberTooLargeException  => {
          logger.error(numberTooLargeException.printStackTrace())
          throw numberTooLargeException
        }
        case numberTooSmallException: NumberTooSmallException  => {
          logger.error(numberTooSmallException.printStackTrace())
          throw numberTooSmallException
        }
        case exception: Exception => { 
          logger.error(exception.printStackTrace())
          throw new Exception("Exception occured while parsing string ["+values+"].") }
      }
    }

  //Q) What should be the default value of mean function ?

  def mean(values: String): AnyVal =
    {
      var mean = None: Option[Double]

      val result = sum(values).asInstanceOf[Double]

      var size = 0

      val numbers = values.split(", ")

      for (n <- numbers) {
        var num = allCatch.opt(n.toDouble)

        if (num != None) {
          size = size + 1
        }
      }

      allCatch.opt(result / size).getOrElse(0.0: Double)

    }

  //Q) What should be the default value of max function ?
  def max(values: String): AnyVal =
    {
      0
    }

}