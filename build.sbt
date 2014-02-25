organization := "com.amaseng"

name := "play2-birt"

version := "1.0-SNAPSHOT"

playScalaSettings

libraryDependencies ++= Seq(
  "org.eclipse.birt.runtime" % "org.eclipse.birt.runtime" % "4.2.0" exclude("org.milyn", "flute"),
  "org.scalatest" % "scalatest_2.10" % "2.0" % "test"
)

publishTo <<= version { v: String =>
       val nexus = "https://oss.sonatype.org/"
       if (v.trim.endsWith("SNAPSHOT")) Some("publish-snapshots" at nexus + "content/repositories/snapshots")
       else                             Some("publish-releases" at nexus + "service/local/staging/deploy/maven2")
     }

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
       <url>http://www.scalatest.org</url>
         <licenses>
           <license>
             <name>the Apache License, ASL Version 2.0</name>
             <url>http://www.apache.org/licenses/LICENSE-2.0</url>
             <distribution>repo</distribution>
           </license>
         </licenses>
         <scm>
           <url>https://github.com/amaseng/play2-birt</url>
           <connection>scm:git:git@github.com:amaseng/play2-birt.git</connection>
           <developerConnection>
             scm:git:git@github.com:amaseng/play2-birt.git
           </developerConnection>
         </scm>
         <developers>
           <developer>
             <id>cheeseng</id>
             <name>Chua Chee Seng</name>
             <email>cheeseng@amaseng.com</email>
           </developer>
         </developers>
      )
