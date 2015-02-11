organization := "edu.umass.cs.iesl"

name := """authexample"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.4"

resolvers ++= Seq(
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
  Resolver.sonatypeRepo("releases"),
  Resolver.url("Edulify Repository", url("http://edulify.github.io/modules/releases/"))(Resolver.ivyStylePatterns)
)

libraryDependencies ++= Seq(
  "com.edulify" %% "play-hikaricp" % "1.4.1",
  "org.postgresql" % "postgresql" % "9.3-1100-jdbc4",
  "com.github.tototoshi" %% "slick-joda-mapper" % "1.2.0",
  "com.typesafe.play" %% "play-slick" % "0.8.0",
  "org.joda" % "joda-convert" % "1.6",
  "com.jsuereth" %% "scala-arm" % "1.4",
  "ws.securesocial" %% "securesocial" % "3.0-M3",
  "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test",
  "edu.umass.cs.iesl" % "authstore_2.11" % "1.0-SNAPSHOT",
  jdbc,
  cache
)

scalacOptions ++= Seq(
  "-target:jvm-1.7",
  "-encoding", "UTF-8",
  //"-deprecation", // warning and location for usages of deprecated APIs
  "-feature", // warning and location for usages of features that should be imported explicitly
  "-unchecked", // additional warnings where generated code depends on assumptions
  "-Xlint", // recommended additional warnings
  "-Ywarn-adapted-args", // Warn if an argument list is modified to match the receiver
  "-Ywarn-value-discard", // Warn when non-Unit expression results are unused
  "-Ywarn-inaccessible",
  "-Ywarn-dead-code",
  "-language:reflectiveCalls"
)

