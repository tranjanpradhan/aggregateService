package aggregateservice.scala.actions

class AggregateActionBigDecimal extends AggregateAction {

  val aggregateSum: AggregateSum = new AggregateSum

  /**
   * Method  "sum" takes argument as "values" [String].The method splits the string [", "] and creates a Array[String]
   * and calls doSumBigDecimalValues method present in AggregateSum class.
   *
   * @name sum
   * @param  values: String
   * @return  BigDecimal
   * @exception NumberFormatException,Exception
   */

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

  /**
   * Method  "mean" takes argument as "values" [String].The method splits the string [", "] and creates a Array[String]
   * and calls doSumBigDecimalValues method present in AggregateSum class.As the doSumBigDecimalValues method returns a tuple,the method
   * just computes the mean.
   *
   * @name mean
   * @param  values: String
   * @return  BigDecimal
   * @exception ArithmeticException,NumberFormatException,Exception
   */

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

  /**
   * Method  "max" takes argument as "values" [String].The method splits the string [", "] and creates a Array[String].
   * It typecasts each element of the array to BigDecimal and finds the maximum value of the array.
   *
   * @name max
   * @param  values: String
   * @return  BigDecimal
   * @exception NumberFormatException,Exception
   */

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
          throw new NumberFormatException("String [" + num + "] cannot be parsed to big decimal.")
        }
        case exception: Exception => {
          println(exception.printStackTrace())
          throw new Exception("Exception occured while parsing string [" + values + "].")
        }
      }
    }

}