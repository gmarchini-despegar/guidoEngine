import sbt.{ModuleID, _}

object Dependencies {
  private object Versions {
    val scalaTest = "3.0.5"
    val scalaXml  = "1.0.+"
  }

  // Test
  val scalatest: ModuleID = "org.scalatest" %% "scalatest" % Versions.scalaTest %
    "test" exclude("org.scala-lang", "scala-reflect") exclude("org.scala-lang.modules", "scala-xml") exclude("org.scala-lang", "scala-library")

  // XML
  val scala_xml = "org.scala-lang.modules" %% "scala-xml" % Versions.scalaXml exclude("org.scala-lang", "scala-library")
}
