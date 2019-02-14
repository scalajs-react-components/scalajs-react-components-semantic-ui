// *****************************************************************************
// Projects
// *****************************************************************************
lazy val `scalajs-react-components-semantic-ui` =
project.in(file("."))
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin, AutomateHeaderPlugin, GitVersioning)
  .settings(settings)
  .settings(commonDependencies)
  .settings(
    scalaJSUseMainModuleInitializer := true,
    webpackResources :=
      webpackResources.value +++
        PathFinder(
          Seq(
            baseDirectory.value / "src" / "main" / "html" / "js",
            baseDirectory.value / "src" / "main" / "html" / "images",
            baseDirectory.value / "src" / "main" / "html" / "css",
            baseDirectory.value / "src" / "main" / "html" / "index.html"
          )) ** "*.*"
  )

// *****************************************************************************
// Settings
// *****************************************************************************

lazy val commonDependencies = Seq(
  resolvers += Resolver.bintrayRepo("oyvindberg", "ScalablyTyped"),
  libraryDependencies ++= Seq(
    "com.github.japgolly.scalajs-react" %%% "core" % "1.4.0" withSources(),
    "com.github.japgolly.scalajs-react" %%% "extra" % "1.4.0" withSources(),
    "org.scala-lang.modules" %%% "scala-parser-combinators" % "1.1.1" withSources(),
    "org.scala-js" %%% "scalajs-dom" % "0.9.6" withSources(),
    "com.github.japgolly.scalacss" %%% "core" % "0.5.5" withSources(),
    "com.github.japgolly.scalacss" %%% "ext-react" % "0.5.5" withSources(),
    "org.scalatest" %% "scalatest" % "3.0.5" % "test" withSources(),
    ScalablyTyped.S.`semantic-ui-react` withSources(),
    ScalablyTyped.R.`react-dom` withSources(),
    ScalablyTyped.R.`react-slick` withSources()
  )
)


lazy val settings =
  bundlerSettings ++
    commonSettings ++
    gitSettings

lazy val SuiVersion = "0.85.0"
lazy val reactVersion = "16.7.0"
lazy val webpackVersion = "4.28.3"

lazy val bundlerSettings =
  Seq(
    version in webpack := webpackVersion,
    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv,
    fork in run := true,
    scalaJSStage in Global := FastOptStage,
    scalaJSUseMainModuleInitializer in Compile := true,
    scalaJSUseMainModuleInitializer in Test := false,
    skip in packageJSDependencies := false,
    artifactPath
      .in(Compile, fastOptJS) := ((crossTarget in(Compile, fastOptJS)).value /
      ((moduleName in fastOptJS).value + "-opt.js")),
    artifactPath
      .in(Compile, fullOptJS) := ((crossTarget in(Compile, fullOptJS)).value /
      ((moduleName in fullOptJS).value + "-opt.js")),
    webpackConfigFile in fastOptJS := Some(baseDirectory.value / "webpack" / "webpack-fastopt.config.js"),
    webpackConfigFile in fullOptJS := Some(baseDirectory.value / "webpack" / "webpack-opt.config.js"),
    webpackConfigFile in Test := Some(baseDirectory.value / "webpack" / "webpack-core.config.js"),
    webpackEmitSourceMaps := true,
    //enableReloadWorkflow := false,
    useYarn := true,
    npmDependencies.in(Compile) := Seq(
      "react"             -> reactVersion,
      "react-dom"         -> reactVersion,
      "semantic-ui-react" -> SuiVersion
    ),
    npmDevDependencies.in(Compile) := Seq(
      "style-loader" -> "0.23.1",
      "css-loader" -> "2.1.0",
      "sass-loader" -> "7.1.0",
      "compression-webpack-plugin" -> "2.0.0",
      "file-loader" -> "3.0.1",
      "gulp-decompress" -> "2.0.2",
      "image-webpack-loader" -> "4.6.0",
      "imagemin" -> "6.1.0",
      "less" -> "3.9.0",
      "less-loader" -> "4.1.0",
      "lodash" -> "4.17.11",
      "node-libs-browser" -> "2.1.0",
      "react-hot-loader" -> "4.6.3",
      "url-loader" -> "1.1.2",
      "expose-loader" -> "0.7.5",
      "webpack" -> webpackVersion
    )
  )

lazy val commonSettings =
  Seq(
    // scalaVersion from .travis.yml via sbt-travisci
    organization := "net.leibman",
    organizationName := "Roberto Leibman",
    startYear := Some(2018),
    licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0")),
    scalacOptions ++= Seq(
      "-P:scalajs:sjsDefinedByDefault",
      "-unchecked",
      "-deprecation",
      "-language:_",
      "-target:jvm-1.8",
      "-encoding", "UTF-8"
    ),
    unmanagedSourceDirectories.in(Compile) := Seq(scalaSource.in(Compile).value),
    unmanagedSourceDirectories.in(Test) := Seq(scalaSource.in(Test).value)
  )

lazy val gitSettings =
  Seq(
    git.useGitDescribe := true
  )

