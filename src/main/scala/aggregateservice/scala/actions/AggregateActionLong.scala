package aggregateservice.scala.actions

class AggregateActionLong extends AggregateAction{
  
    def sum(values:String):AnyVal=
    {
      var result =None:Option[Long];
      
      val numbers=values.split(", ");
      
      for (n <- numbers)
      {
        result = Some(result.getOrElse(0:Long)+n.toLong)
      }
       result.getOrElse(0:Long)
    }
}