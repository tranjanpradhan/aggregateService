package aggregateservice.scala.actions

trait AggregateSumAction {
  def doSumDoubleValues(values: Array[String]): (Double, Integer)
  
   def doSumLongValues(values: Array[String]): (Long, Integer)
  
  //def doSumLongValues(values: Array[String]): (Long, Integer)
  
  
}