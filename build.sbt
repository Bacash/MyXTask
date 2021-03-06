name := """ProbamMongoDB"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)



scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "org.reactivemongo" %% "reactivemongo" % "0.11.14"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
