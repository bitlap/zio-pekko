val mainScala = "3.3.1"
val allScala  = Seq("2.13.11", mainScala)

val zioVersion   = "2.0.15"
val pekkoVersion = "1.0.2"

inThisBuild(
  List(
    organization             := "org.bitlap",
    homepage                 := Some(url("https://github.com/bitlap/zio-pekko")),
    licenses                 := List("Apache-2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0")),
    scalaVersion             := mainScala,
    crossScalaVersions       := allScala,
    Test / parallelExecution := false,
    Test / fork              := true,
    sonatypeCredentialHost   := "s01.oss.sonatype.org",
    sonatypeRepository       := "https://s01.oss.sonatype.org/service/local",
    scmInfo                  := Some(
      ScmInfo(url("https://github.com/bitlap/zio-pekko"), "scm:git:git@github.com:bitlap/zio-pekko.git")
    ),
    developers               := List(
      Developer(
        "jxnu-liguobin",
        "jxnu-liguobin",
        "dreamylost@outlook.com",
        url("https://github.com/jxnu-liguobin")
      )
    ),
    scalacOptions ++= Seq(
      "-encoding",
      "UTF-8",
      "-feature",
      "-language:higherKinds",
      "-language:existentials",
      "-unchecked",
      "-deprecation"
    )
  )
)

lazy val `zio-pekko` =
  project.in(file(".")).aggregate(`zio-pekko-cluster`)

lazy val `zio-pekko-cluster` = project
  .in(file("zio-pekko-cluster"))
  .settings(
    name := "zio-pekko-cluster",
    libraryDependencies ++= Seq(
      "dev.zio"          %% "zio"                    % zioVersion,
      "dev.zio"          %% "zio-streams"            % zioVersion,
      "org.apache.pekko" %% "pekko-cluster-tools"    % pekkoVersion,
      "org.apache.pekko" %% "pekko-cluster-sharding" % pekkoVersion,
      "dev.zio"          %% "zio-test"               % zioVersion % "test",
      "dev.zio"          %% "zio-test-sbt"           % zioVersion % "test"
    ),
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  )

run / fork := true

addCommandAlias("fmt", "all scalafmtSbt scalafmt test:scalafmt")
addCommandAlias("check", "all scalafmtSbtCheck scalafmtCheck test:scalafmtCheck")
