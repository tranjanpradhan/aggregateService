package aggregateservice

import org.scalatest.mockito.MockitoSugar
import org.mockito.Mockito._
import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import org.scalactic.source.Position.apply
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import aggregateservice.scala.actions._ 

@RunWith(classOf[JUnitRunner])
class AggregateServiceLauncher extends FunSuite with BeforeAndAfter with MockitoSugar{
  
test ("test login service")
{
  val service = mock[AggregateActionDouble]
  
  service.
  
  //service.doAggregate(function, values, valueType)
  
}


} 