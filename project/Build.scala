import sbt._

import Keys._
import AndroidKeys._
import AndroidNdkKeys._

object General {
  // Some basic configuration
  val settings = Defaults.defaultSettings ++ Seq (
    name := "clickcounter-android-scala",
    version := "0.1",
    versionCode := 0,
    scalaVersion := "2.10.1-RC1",
    platformName in Android := "android-17"
  )

  // Default Proguard settings
  lazy val proguardSettings = inConfig(Android) (Seq (
    useProguard := true,
    proguardOptimizations += "-keep class edu.luc.etl.cs313.android.scala.** { *; }"
  ))

  // Example NDK settings
  lazy val ndkSettings = AndroidNdk.settings ++ inConfig(Android) (Seq(
    jniClasses := Seq(),
    javahOutputFile := Some(new File("native.h"))
  ))

  // Full Android settings
  lazy val fullAndroidSettings =
    General.settings ++
    AndroidProject.androidSettings ++
    TypedResources.settings ++
    proguardSettings ++
    AndroidManifestGenerator.settings ++
    AndroidMarketPublish.settings ++ Seq (
      keyalias in Android := "change-me",
      libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0.M5b" % "test"
    )
}

object AndroidBuild extends Build {
  lazy val main = Project (
    "main",
    file("."),
    settings = General.fullAndroidSettings ++ AndroidEclipseDefaults.settings
  )

  lazy val tests = Project (
    "tests",
    file("tests"),
    settings = General.settings ++
               AndroidEclipseDefaults.settings ++
               AndroidTest.androidSettings ++
               General.proguardSettings ++ Seq (
      name := "clickcounter-android-scalaTests"
    )
  ) dependsOn main
}
