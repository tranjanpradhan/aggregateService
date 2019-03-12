package aggregateservice.scala.actions

import scala.util.control.Exception.allCatch
import org.apache.log4j.Logger

class AggregateActionBigDecimal extends AggregateAction {

  @transient lazy val logger = Logger.getLogger(getClass.getName)
  val aggregateSum: AggregateSum = new AggregateSum

  @throws(classOf[Exception])
  def sum(values: String): BigDecimal =
    {
      logger.info(s"String for which sum needs to be calculated [" + values + "].")
      var numbers: Array[String] = null
      try {
        numbers = values.split(", ")
        aggregateSum.doSumBigDecimalValues(numbers)._1
      } catch {
        case numberFormatException: NumberFormatException => {
          logger.error(numberFormatException.printStackTrace())
          throw numberFormatException
        }
        case exception: Exception => {
          logger.error(exception.printStackTrace())
          throw new Exception("Exception occured while parsing string [" + values + "].")
        }
      }
    }

 @throws(classOf[Exception])
  def mean(values: String): Any =
    {
      logger.info(s"String for which mean needs to be calculated ["+values+"].")
      var numbers: Array[String] = null
      var mean:BigDecimal=0.0d
      try {
        numbers = values.split(", ")
        val sumDetails = aggregateSum.doSumBigDecimalValues(numbers)
        mean=sumDetails._1/BigDecimal(sumDetails._2)
        logger.info(s"Mean for ["+values+"] is" + mean + ".")
        mean
      } catch {
        case numberFormatException: NumberFormatException  => {
          logger.error(numberFormatException.printStackTrace())
          throw numberFormatException
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