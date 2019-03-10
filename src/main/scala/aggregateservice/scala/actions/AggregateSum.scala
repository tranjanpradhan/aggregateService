package aggregateservice.scala.actions

import org.apache.log4j.Logger


class AggregateSum extends AggregateSumAction {

   @transient lazy val logger = Logger.getLogger(getClass.getName)
  
  @throws(classOf[NumberFormatException])
  def doSumDoubleValues(values: Array[String]): (Double, Integer) =
    {
      var sum:Double = 0.0
      var size = 0
      try {
        for (n <- values) {
          logger.debug(s"Double Value to be added:-["+n+"].")
          sum = sum + n.toDouble
          logger.debug(s"Current sum is:-["+sum+"].")
          size = size + 1
          logger.debug(s"Number of elements traversed are:-["+size+"].")
        }
      } catch {
        case exception: NumberFormatException => { throw new NumberFormatException("String [" + values(size) + "] cannot be parsed to double.") }
      }
      logger.info(s"Sum for double values is ["+sum+"] and total number of elements are ["+size+"].")
      (sum, size)
    }
}