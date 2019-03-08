name := "aggregateservice"

version := "0.1"

scalaVersion := "2.12.7"

resolvers ++= Seq("Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/")
 
libraryDependencies ++= {
  val akkaVersion       = "2.4.20"
  val akkaHttpVersion   = "10.0.11"
  Seq(
    "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
    "io.spray"          %% "spray-json"           % "1.3.1",
    "com.typesafe.akka" %% "akka-slf4j"           % akkaVersion,
    "ch.qos.logback"    %  "logback-classic"      % "1.1.2"
  )
}


seq(Revolver.settings: _*)
