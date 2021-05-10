name := "pfp-scala"

version := "0.1"

scalaVersion := "2.13.5"

scalacOptions ++= List(
  "-Ymacro-annotations"
)

lazy val core = (project in file("core"))
  .settings(
    libraryDependencies ++= List(
      compilerPlugin("org.typelevel"  %% "kind-projector"  % "0.11.3" cross CrossVersion.full),
      compilerPlugin("org.augustjune" %% "context-applied" % "0.1.4"),
      "org.typelevel"              %% "cats-core"           % "2.6.0",
      "org.typelevel"              %% "cats-effect"         % "2.5.0",
      "dev.profunktor"             %% "console4cats"        % "0.8.1",
      "org.manatki"                %% "derevo-cats"         % "0.11.6",
      "org.manatki"                %% "derevo-cats-tagless" % "0.11.6",
      "co.fs2"                     %% "fs2-core"            % "2.2.2",
      "com.olegpy"                 %% "meow-mtl-core"       % "0.4.1",
      "com.olegpy"                 %% "meow-mtl-effects"    % "0.4.1",
      "io.estatico"                %% "newtype"             % "0.4.4",
      "eu.timepit"                 %% "refined"             % "0.9.12",
      "com.github.julien-truffaut" %% "monocle-core"        % "2.1.0",
      "com.github.julien-truffaut" %% "monocle-macro"       % "2.1.0"
    )
  )

lazy val server = (project in file("server"))
  .dependsOn(core)

lazy val `pfp-scala` = (project in file("."))
  .aggregate(server)
