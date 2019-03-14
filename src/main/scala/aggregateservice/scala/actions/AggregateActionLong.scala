package aggregateservice.scala.actions

import aggregateservice.custom.exceptions._

class AggregateActionLong extends AggregateAction {

val aggregateSum: AggregateSum = new AggregateSum

  /**
   * Method  "sum" takes argument as "values" [String].The method splits the string [", "] and creates a Array[String]
   * and calls doSumLongValues method present in AggregateSum class.
   *
   * @name sum
   * @param  values: String
   * @return  Long
   * @exception NumberTooLargeException,NumberTooSmallException,NumberFormatException,Exception
   */

  def sum(values: String): Long =
    {
      println(s"String for which sum needs to be calculated [" + values + "].")
      var numbers: Array[String] = null
      try {
        numbers = values.split(", ")
        aggregateSum.doSumLongValues(numbers)._1
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

  /**
   * Method  "mean" takes argument as "values" [String].The method splits the string [", "] and creates a Array[String]
   * and calls doSumLongValues method present in AggregateSum class.As the doSumLongValues method returns a tuple,the method
   * just computes the mean.
   *
   * @name mean
   * @param  values: String
   * @return  Long
   * @exception ArithmeticException,NumberTooLargeException,NumberTooSmallException,NumberFormatException,Exception
   */

  def mean(values: String): Long =
    {
      println(s"String for which mean needs to be calculated [" + values + "].")
      var numbers: Array[String] = null
      var mean: Long = 0l
      try {
        numbers = values.split(", ")
        val sumDetails = aggregateSum.doSumLongValues(numbers)
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

  /**
   * Method  "max" takes argument as "values" [String].The method splits the string [", "] and creates a Array[String].
   * It typecasts each element of the array to Long and finds the maximum value of the array.
   *
   * @name max
   * @param  values: String
   * @return  Long
   * @exception NumberTooLargeException,NumberTooSmallException,NumberFormatException,Exception
   */


  def max(values: String): Long =
    {
      println(s"String for which max needs to be found [" + values + "].")
      var numbers: Array[String] = null
      var max: Long = Long.MinValue
      var num: String = ""
      try {
        numbers = values.split(", ")

        for (n <- numbers) {
          if (n.trim.endsWith("l") || n.trim.endsWith("L")) {
            num = n.trim().dropRight(1)
          } else {
            num = n
          }

          if (BigInt(num) > BigInt(Long.MaxValue)) throw new NumberTooLargeException(num + ": value is too large for long data type.")
          else if (BigInt(num) < BigInt(Long.MinValue)) throw new NumberTooSmallException(num + ": value is too small for long data type.")

          var tempNumber = num.toLong

          if (tempNumber > max) {
            max = tempNumber
          }
        }
        println(s"Max for [" + values + "] is" + max + ".")
        max
      } catch {
        case numberFormatException: NumberFormatException => {
          println(numberFormatException.printStackTrace())
          throw new NumberFormatException("String [" + num + "] cannot be parsed to long.")
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