package aggregateservice.scala.actions


import scala.util.control.Exception.allCatch

class AggregateActionDouble extends AggregateAction{
  
    def sum(values:String):AnyVal=
    {
      var result=None:Option[Double];
      
      val numbers=values.split(", ");
      
      for (n <- numbers)
      {
        var num=allCatch.opt(n.toDouble).getOrElse(0.0:Double)
        result = Some(result.getOrElse(0.0:Double)+num)
      }
 
       result.getOrElse(0.0:Double)
    }
 
 //Q) What should be the default value of mean function ?   
    
  def mean(values:String):AnyVal=
  {
    var mean=None:Option[Double]
    
    var result=sum(values).asInstanceOf[Double]
    
    var size=0
    
     val numbers=values.split(", ")
    
     for (n <- numbers)
      {
        var num=allCatch.opt(n.toDouble)
        
        if (num!=None)
        {
          size=size+1
        }
      }
 
    
    mean = Some(result/size)
    
    mean.getOrElse(0.0:Double)
    
  }
  
  //Q) What should be the default value of max function ?
  def max(values:String):AnyVal=
  {
    0
  }
  
  
}