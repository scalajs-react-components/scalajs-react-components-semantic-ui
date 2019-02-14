addSbtPlugin("com.dwijnand"      % "sbt-dynver"          % "3.1.0")
addSbtPlugin("com.geirsson"      % "sbt-scalafmt"        % "1.5.1")
addSbtPlugin("de.heikoseeberger" % "sbt-header"          % "5.1.0")
addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.26")
addSbtPlugin("ch.epfl.scala"     % "sbt-scalajs-bundler" % "0.14.0")
addSbtPlugin("com.dwijnand"      % "sbt-travisci"        % "1.1.3")

libraryDependencies += "org.slf4j" % "slf4j-nop" % "1.7.25" // Needed by sbt-git

resolvers += Resolver.bintrayRepo("oyvindberg", "ScalablyTyped")
addSbtPlugin("org.scalablytyped" % "sbt-scalablytyped" % "201902140907")