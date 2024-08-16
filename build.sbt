ThisBuild / version := "1.0.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.5.0"

lazy val root = (project in file("."))
  .settings(
    name := "composerr",
    idePackagePrefix := Some("cl.ravenhill.composerr")
  )

libraryDependencies += "org.virtuslab" %% "scala-yaml" % "0.2.0"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.19"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % "test"
libraryDependencies += "org.scalatestplus" %% "scalacheck-1-18" % "3.2.19.0" % "test"
