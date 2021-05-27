ThisBuild / organization := "com.dragishak"

ThisBuild / version := "0.1"

ThisBuild / scalaVersion := "2.13.6"

ThisBuild / scalacOptions ++= List(
  "-Ymacro-annotations"
)

val versions = new {
  val cats                = "2.6.1"
  val catsTestkit         = "2.1.4"
  val console4cats        = "0.8.1"
  val eff                 = "2.5.0"
  val fs2                 = "2.5.5"
  val circe               = "0.14.0"
  val http4s              = "0.21.22"
  val derevo              = "0.11.6"
  val newtype             = "0.4.4"
  val meow                = "0.4.1"
  val monocle             = "2.1.0"
  val scalacheck          = "1.15.4"
  val refined             = "0.9.12"
  val squants             = "1.8.0"
  val scalatest           = "3.2.9"
  val disciplineScalatest = "2.1.5"
  val scalatestScalacheck = "3.2.9.0"
}

lazy val core = (project in file("core"))
  .settings(
    name := "pfp-scala-core",
    libraryDependencies ++= List(
      compilerPlugin("org.typelevel"  %% "kind-projector"  % "0.13.0" cross CrossVersion.full),
      compilerPlugin("org.augustjune" %% "context-applied" % "0.1.4"),
      "org.typelevel"              %% "cats-core"              % versions.cats,
      "org.typelevel"              %% "cats-effect"            % versions.eff,
      "dev.profunktor"             %% "console4cats"           % versions.console4cats,
      "org.manatki"                %% "derevo-cats"            % versions.derevo,
      "org.manatki"                %% "derevo-cats-tagless"    % versions.derevo,
      "co.fs2"                     %% "fs2-core"               % versions.fs2,
      "com.olegpy"                 %% "meow-mtl-core"          % versions.meow,
      "com.olegpy"                 %% "meow-mtl-effects"       % versions.meow,
      "io.estatico"                %% "newtype"                % versions.newtype,
      "eu.timepit"                 %% "refined"                % versions.refined,
      "com.github.julien-truffaut" %% "monocle-core"           % versions.monocle,
      "com.github.julien-truffaut" %% "monocle-macro"          % versions.monocle,
      "org.typelevel"              %% "squants"                % versions.squants,
      "org.scalatest"              %% "scalatest"              % versions.scalatest           % Test,
      "org.typelevel"              %% "cats-testkit-scalatest" % versions.catsTestkit         % Test,
      "org.typelevel"              %% "cats-laws"              % versions.cats                % Test,
      "org.typelevel"              %% "discipline-scalatest"   % versions.disciplineScalatest % Test,
      "org.scalatestplus"          %% "scalacheck-1-15"        % versions.scalatestScalacheck % Test,
      "org.scalacheck"             %% "scalacheck"             % versions.scalacheck          % Test
    )
  )

lazy val server = (project in file("server"))
  .dependsOn(core)
  .settings(
    name := "pfp-scala-server",
    libraryDependencies ++= List(
      "org.http4s" %% "http4s-blaze-server" % versions.http4s,
      "org.http4s" %% "http4s-circe"        % versions.http4s,
      "io.circe"   %% "circe-core"          % versions.circe,
      "io.circe"   %% "circe-generic"       % versions.circe,
      "io.circe"   %% "circe-refined"       % versions.circe
    )
  )

lazy val `pfp-scala` = (project in file("."))
  .aggregate(server, core)
  .settings(
    name := "pfp-scala"
  )
