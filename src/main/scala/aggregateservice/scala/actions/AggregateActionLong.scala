package aggregateservice.scala.actions

import scala.util.control.Exception.allCatch
import org.apache.log4j.Logger

class AggregateActionLong extends AggregateAction {

  @transient lazy val logger = Logger.getLogger(getClass.getName)
  val aggregateSum: AggregateSum =new AggregateSum

 @throws(classOf[Exception])
  def sum(values: String): AnyVal =
    {
      logger.info(s"String for which sum needs to be calculated ["+values+"].")
      var numbers: Array[String] = null
      try {
        numbers = values.split(", ")
        aggregateSum.doSumLongValues(numbers)._1
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

   @throws(classOf[Exception])
  def mean(values: String): AnyVal =
    {
      logger.info(s"String for which mean needs to be calculated ["+values+"].")
      var numbers: Array[String] = null
      var mean:Long=0l
      try {
        numbers = values.split(", ")
        val sumDetails = aggregateSum.doSumLongValues(numbers)
        mean=sumDetails._1/sumDetails._2
        logger.info(s"Mean for ["+values+"] is" + mean + ".")
        mean
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
        case arithmeticException:ArithmeticException => {
          logger.error(arithmeticException.printStackTrace())
         throw new ArithmeticException("Exception occured while computing mean.") }
        case exception: Exception => { 
          logger.error(exception.printStackTrace())
          throw new Exception("Exception occured while parsing string ["+values+"].") }
      }
    }

  //Q) What should be the default value of max function ?
  def max(values: String): AnyVal =
    {
      0
    }

}