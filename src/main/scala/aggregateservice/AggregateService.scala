package aggregateservice

import aggregateservice.actions._
import akka.actor.{Actor, ActorLogging}
import spray.json.DefaultJsonProtocol._

final case class AggregateRequest(function: String, values: String, valueType: String)
final case class Result(result: String)
final case class Error(message: String)
final case class AggregateResponse(result: Option[Result], error: Option[Error])

object AggregateServiceProtocol {
  implicit val requestFormat = jsonFormat3(AggregateRequest)
  implicit val errorFormat = jsonFormat1(Error)
  implicit val resultFormat = jsonFormat1(Result)
  implicit val aggregateResponseFormat = jsonFormat2(AggregateResponse)
}

class AggregateService extends Actor with ActorLogging {
  implicit val system = context.system

  def receive = {
    case AggregateRequest(function, values, valueType) =>
      log.info(s"Received aggregate request")
      sender() ! doAggregate(function, values, valueType)
      context.stop(self)
    case other                                         =>
      log.debug(s"Received an unknown message: $other")
      context.stop(self)
  }

  def doAggregate(function: String, values: String, valueType: String): AggregateResponse = {
    try
    {
       valueType match {
      case "long"   =>
        val longAction = new AggregateActionLong
        function match {
          case "sum"  => AggregateResponse(Some(Result(longAction.sum(values).toString)), None)
          case "mean" => AggregateResponse(Some(Result(longAction.mean(values).toString)), None)
          case "max"  => AggregateResponse(Some(Result(longAction.max(values).toString)), None)
          case other  => AggregateResponse(None, Some(Error(s"Received unknown aggregate function: $other")))
        }
      case "double" =>
        val doubleAction = new AggregateActionDouble
        function match {
          case "sum"  => AggregateResponse(Some(Result(doubleAction.sum(values).toString)), None) 
          case "mean" => AggregateResponse(Some(Result(doubleAction.mean(values).toString)), None)
          case "max"  => AggregateResponse(Some(Result(doubleAction.max(values).toString)), None)
          case other  => AggregateResponse(None, Some(Error(s"Received unknown aggregate function: $other")))
        }
      case other    =>
        AggregateResponse(None, Some(Error(s"Received unknown value type: $other")))
      }
    }
    catch
    {
      case exception: Exception => {
          AggregateResponse(None, Some(Error(exception.getMessage)))
      }
    }
  }
}