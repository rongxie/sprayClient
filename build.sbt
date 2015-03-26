name := "Hello Test"

scalaVersion := "2.11.6"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases"
resolvers += "spray repo" at "http://repo.spray.io"
resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

libraryDependencies ++= Seq("com.typesafe.akka" %% "akka-actor" % "2.3.9",
"io.spray" %% "spray-can" % "1.3.2",
"io.spray" %% "spray-routing" % "1.3.1",
"io.spray" %% "spray-testkit" % "1.3.1" % "test",
"org.specs2" %% "specs2-core" % "3.1.1" % "test",
"io.spray" %% "spray-json"   % "1.3.1",
"io.spray" %%  "spray-client" % "1.3.2"
)
scalacOptions in Test ++= Seq("-Yrangepos")