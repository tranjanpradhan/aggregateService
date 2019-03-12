package aggregateservice

import akka.actor.ActorSystem
import akka.actor.Props
import akka.http.scaladsl.model._
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives.entity
import akka.pattern.ask
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import scala.concurrent.Future
import scala.util.Failure
import scala.util.Success
import StatusCodes._

object AggregateServiceLauncher extends App {
  implicit val system = ActorSystem("aggregate-system")

  val config = ConfigFactory.load()
  val host   = config.getString("http.host")
  val port   = config.getInt("http.port")

  import akka.util.Timeout
  import scala.concurrent.duration._
  import aggregateservice.AggregateServiceProtocol._

  implicit val timeout          = Timeout(10 seconds)
  implicit val executionContext = system.dispatcher
  implicit val materializer     = ActorMaterializer()

  val route =
    post {
      entity(as[AggregateRequest]) { request =>
        val aggregateService                  = system.actorOf(Props(new AggregateService()), "aggregate-service")
        val result: Future[AggregateResponse] = (aggregateService ? request).mapTo[AggregateResponse]
        onComplete(result) {
          case Success(r) => complete(r)
          case Failure(f) => complete(HttpResponse(InternalServerError, entity = s"An exception occurred: ${f.getMessage}"))
        }
      }
    }

  Http().bindAndHandle(route, host, port).onComplete {
    case Success(address) => println(s"Successfully connected to $address")
    case Failure(t) =>
      println(s"Could not connect to $host:$port, ${t.getMessage}")
      system.terminate()
  }
}