ThisBuild / organization := "com.dragishak"

ThisBuild / version := "0.1"

ThisBuild / scalaVersion := "2.13.5"

ThisBuild / scalacOptions ++= List(
  "-Ymacro-annotations"
)

lazy val core = (project in file("core"))
  .settings(
    name := "pfp-scala-core",
    libraryDependencies ++= List(
      compilerPlugin("org.typelevel"  %% "kind-projector"  % "0.11.3" cross CrossVersion.full),
      compilerPlugin("org.augustjune" %% "context-applied" % "0.1.4"),
      "org.typelevel"              %% "cats-core"           % "2.6.0",
      "org.typelevel"              %% "cats-effect"         % "2.5.0",
      "dev.profunktor"             %% "console4cats"        % "0.8.1",
      "org.manatki"                %% "derevo-cats"         % "0.11.6",
      "org.manatki"                %% "derevo-cats-tagless" % "0.11.6",
      "co.fs2"                     %% "fs2-core"            % "2.5.5",
      "com.olegpy"                 %% "meow-mtl-core"       % "0.4.1",
      "com.olegpy"                 %% "meow-mtl-effects"    % "0.4.1",
      "io.estatico"                %% "newtype"             % "0.4.4",
      "eu.timepit"                 %% "refined"             % "0.9.12",
      "com.github.julien-truffaut" %% "monocle-core"        % "2.1.0",
      "com.github.julien-truffaut" %% "monocle-macro"       % "2.1.0",
      "org.typelevel"              %% "squants"             % "1.8.0"
    )
  )

lazy val server = (project in file("server"))
  .dependsOn(core)
  .settings(
    name := "pfp-scala-server",
    libraryDependencies ++= List(
      "org.http4s" %% "http4s-blaze-server" % "0.21.22",
      "org.http4s" %% "http4s-circe"        % "0.21.22",
      "io.circe"   %% "circe-core"          % "0.13.0",
      "io.circe"   %% "circe-generic"       % "0.13.0",
      "io.circe"   %% "circe-refined"       % "0.13.0"
    )
  )

lazy val `pfp-scala` = (project in file("."))
  .aggregate(server)
  .settings(
    name := "pfp-scala"
  )
