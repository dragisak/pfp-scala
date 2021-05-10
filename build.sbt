name := "pfp-scala"

version := "0.1"

scalaVersion := "2.13.5"

libraryDependencies ++= List(
  "io.estatico" %% "newtype" % "0.4.4"
)

scalacOptions ++= List(
  "-Ymacro-annotations"
)