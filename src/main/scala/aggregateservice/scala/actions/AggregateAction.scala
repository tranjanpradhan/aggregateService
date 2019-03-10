package aggregateservice.scala.actions

trait AggregateAction {
  
  //We expect default return value of zero for sum function 
  def sum(values:String):AnyVal
 
  def mean(values:String):AnyVal
  
  def max(values:String):AnyVal

}



