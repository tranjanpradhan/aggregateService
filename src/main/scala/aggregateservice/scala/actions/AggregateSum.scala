package aggregateservice.scala.actions

import aggregateservice.custom.exceptions._

class AggregateSum extends AggregateSumAction {

  /**
   * Method  "doSumDoubleValues" takes argument as "values" Array[String].The method traverses the array ,typecasts
   * each element of the array to double and the computes the sum.It returns a tuple of two elements.The first element
   * of the tuple is the sum of the elements which is of double type and the second one is the number of elements
   * present in the input array which is of integer type.Any element in the array if it is not of the double type
   * then the NumberFormatException is thrown.If any element of the array is greater than max value of the double then
   * custom exception NumberTooLargeException is thrown and if any element of the array is lesser than min value of double then
   *  custom exception NumberTooSmallException is thrown.
   *
   * @name doSumDoubleValues
   * @param  values: Array[String]
   * @return  Tuple2[Double, Integer]
   * @exception NumberTooLargeException,NumberTooSmallException,NumberFormatException
   */

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

          println(s"Double Value to be added:-[" + num + "].")
          sum = sum + num.toDouble
          println(s"Current sum is:-[" + sum + "].")
          size = size + 1
          println(s"Number of elements traversed are:-[" + size + "].")
        }
      } catch {
        case exception: NumberFormatException => { throw new NumberFormatException("String [" + values(size) + "] cannot be parsed to double.") }
      }
      println(s"Sum for double values is [" + sum + "] and total number of elements are [" + size + "].")
      (sum, size)
    }

  /**
   * Method  "doSumLongValues" takes argument as "values" Array[String].The method traverses the array ,typecasts
   * each element of the array to long and the computes the sum.It returns a tuple of two elements.The first element
   * of the tuple is the sum of the elements which is of long type and the second one is the number of elements
   * present in the input array which is of integer type.Any element in the array if it is not of the long type
   * then the NumberFormatException is thrown.If any element of the array is greater than max value of the long then
   * custom exception NumberTooLargeException is thrown and if any element of the array is lesser than min value of long then
   * custom exception NumberTooSmallException is thrown.
   *
   * @name doSumLongValues
   * @param  values: Array[String]
   * @return  Tuple2[Long, Integer]
   * @exception NumberTooLargeException,NumberTooSmallException,NumberFormatException
   */

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

          println(s"Long Value to be added:-[" + num + "].")
          sum = sum + num.toLong
          println(s"Current sum is:-[" + sum + "].")
          size = size + 1
          println(s"Number of elements traversed are:-[" + size + "].")
        }
      } catch {
        case exception: NumberFormatException => { throw new NumberFormatException("String [" + values(size) + "] cannot be parsed to long.") }
      }
      println(s"Sum for long values is [" + sum + "] and total number of elements are [" + size + "].")
      (sum, size)
    }

  /**
   * Method  "doSumBigDecimalValues" takes argument as "values" Array[String].The method traverses the array ,typecasts
   * each element of the array to bigdecimal and the computes the sum.It returns a tuple of two elements.The first element
   * of the tuple is the sum of the elements which is of bigdecimal type and the second one is the number of elements
   * present in the input array which is of integer type.Any element in the array if it is not of the bigdecimal type
   * then the NumberFormatException is thrown.
   *
   * @name doSumBigDecimalValues
   * @param  values:Array[String]
   * @return  Tuple2[BigDecimal, Integer]
   * @exception NumberFormatException
   */

  def doSumBigDecimalValues(values: Array[String]): (BigDecimal, Integer) =
    {
      var sum: BigDecimal = 0.0
      var size = 0
      var num: String = ""
      try {

        for (n <- values) {

          if (n.trim.endsWith("d") || n.trim.endsWith("D")) {
            num = n.trim().dropRight(1)
          } else {
            num = n
          }
          println(s"BigDecimal Value to be added:-[" + num + "].")
          sum = sum + BigDecimal(num)
          println(s"Current sum is:-[" + sum + "].")
          size = size + 1
          println(s"Number of elements traversed are:-[" + size + "].")
        }
      } catch {
        case exception: NumberFormatException => { throw new NumberFormatException("String [" + values(size) + "] cannot be parsed to BigDecimal.") }
      }
      println(s"Sum for bigdecimal values is [" + sum + "] and total number of elements are [" + size + "].")
      (sum, size)
    }
}