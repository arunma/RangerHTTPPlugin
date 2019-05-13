name := "RangerHTTPPlugin"

version := "0.1"

scalaVersion := "2.11.12"

libraryDependencies ++= Seq(
  "org.apache.ranger" % "ranger-plugins-common" % "1.2.0" % Provided,
  "com.softwaremill.sttp" %% "core" % "1.5.16"
)

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs@_*) => MergeStrategy.discard
  case _ => MergeStrategy.first
}

test in assembly := {}

fork in Test := true
javaOptions ++= Seq("-Xms2g", "-Xmx3g", "-XX:+CMSClassUnloadingEnabled")
parallelExecution in Test := true
