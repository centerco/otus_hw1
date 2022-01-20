
name := "hw1-geo"

ThisBuild / scalaVersion := "2.12.10"
ThisBuild / version := "0.1"

scalacOptions ++= Seq("-target:jvm-1.8")

val circeVersion = "0.14.1"
libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)


