import Dependencies._

name := "guidoEngine"

version := "0.1"

scalaVersion := "2.12.10"

lazy val crawlerDependencies = Seq(
  // test
  scalatest,

  // XML
  scala_xml
)


lazy val crawler = Project(id = "crawler", base = file("crawler"))
  .settings(Seq(
    name := "crawler",
    startYear := Some(2019),
    organization := "AgileEngine.recruiting",
    mainClass in assembly := Some("crawler.Main"),
    assemblyJarName in assembly := "GMARCHINI-crawler.jar"
  ))
  .settings(libraryDependencies ++= crawlerDependencies)




lazy val screening = Project(id = "screening", base = file("screening"))
  .settings(Seq(
    name := "screening",
    startYear := Some(2019),
    organization := "AgileEngine.recruiting"
  ))
  .settings(libraryDependencies += scalatest)