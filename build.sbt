import Dependencies._

name := "guidoEngine"

version := "0.1"

scalaVersion := "2.12.10"

lazy val settings = Seq(
  name := "AgileEngine.GuidoMarchini",
  startYear := Some(2019),
  organization := "AgileEngine.recruiting"
)

lazy val dependencies = Seq(
  // test
  scalatest,
  mockito,
  
  // XML
  scala_xml
)


lazy val agileEngine = Project(id = "crawler", base = file("crawler"))
  .settings(settings: _*)
  .settings(libraryDependencies ++= dependencies)