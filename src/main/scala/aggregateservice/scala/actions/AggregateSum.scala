package aggregateservice.scala.actions

import org.apache.log4j.Logger

class AggregateSum extends AggregateSumAction {

  @transient lazy val logger = Logger.getLogger(getClass.getName)

  def doSumDoubleValues(values: Array[String]): (Double, Integer) =
    {
      var sum: Double = 0.0
      var size = 0
      var num: String = ""
      try {

        for (n <- values) {

          if (n.trim.endsWith("d") || n.trim.endsWith("D")) {
            num = n.trim().dropRight(1)
          } else {
            num = n
          }

          if (BigDecimal(num) > BigDecimal(Double.MaxValue)) {
            throw new NumberTooLargeException(num + ": value is too large for double data type.")
          } else if (BigDecimal(num) < BigDecimal(Double.MinValue)) {
            throw new NumberTooSmallException(num + ": value is too small for double data type.")
          }

          logger.debug(s"Double Value to be added:-[" + num + "].")
          sum = sum + num.toDouble
          logger.debug(s"Current sum is:-[" + sum + "].")
          size = size + 1
          logger.debug(s"Number of elements traversed are:-[" + size + "].")
        }
      } catch {
        case exception: NumberFormatException => { throw new NumberFormatException("String [" + values(size) + "] cannot be parsed to double.") }
      }
      logger.info(s"Sum for double values is [" + sum + "] and total number of elements are [" + size + "].")
      (sum, size)
    }

  def doSumLongValues(values: Array[String]): (Long, Integer) =
    {
      var sum = 0L
      var size = 0
      var num: String = ""
      try {
        for (n <- values) {

          if (n.trim.endsWith("l") || n.trim.endsWith("L")) {
            num = n.trim().dropRight(1)
          } else {
            num = n
          }

          if (BigInt(num) > BigInt(Long.MaxValue)) {
            throw new NumberTooLargeException(num + ": value is too large for long data type.")
          } else if (BigInt(num) < BigInt(Long.MinValue)) {
            throw new NumberTooSmallException(num + ": value is too small for long data type.")
          }

          logger.debug(s"Long Value to be added:-[" + num + "].")
          sum = sum + num.toLong
          logger.debug(s"Current sum is:-[" + sum + "].")
          size = size + 1
          logger.debug(s"Number of elements traversed are:-[" + size + "].")
        }
      } catch {
        case exception: NumberFormatException => { throw new NumberFormatException("String [" + values(size) + "] cannot be parsed to long.") }
      }
      logger.info(s"Sum for long values is [" + sum + "] and total number of elements are [" + size + "].")
      (sum, size)
    }
}