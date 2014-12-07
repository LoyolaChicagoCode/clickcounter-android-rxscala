// Slightly complicated build file for use with pfn's excellent
// Android Scala sbt plugin.
//
// Please see here for details:
// https://github.com/pfn/android-sdk-plugin/blob/master/README.md

import android.Keys._

android.Plugin.androidBuild

organization := "lucoodevcourse"

name := "clickcounter-android-rxscala"

version := "0.2"

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

javacOptions ++= Seq("-target", "1.6", "-source", "1.6") // so we can build with Java 7 or 8

scalacOptions in Compile ++= Seq("-feature", "-unchecked", "-deprecation")

platformTarget in Android := "android-19"

libraryDependencies ++= Seq(
  "org.robolectric" % "robolectric" % "2.3" % "test",
  "junit" % "junit" % "4.11" % "test",
  "org.scalamock" %% "scalamock-scalatest-support" % "3.2" % "test",
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "com.netflix.rxjava" % "rxjava-core" % "0.20.7",
  "com.netflix.rxjava" % "rxjava-scala" % "0.20.7",
  "com.netflix.rxjava" % "rxjava-android" % "0.20.7"
)

val androidJars = (platformJars in Android, baseDirectory) map {
  (j, b) => Seq(Attributed.blank(b / "bin" / "classes"), Attributed.blank(file(j._1)))
}

// Make the actually targeted Android jars available to Robolectric for shadowing.
managedClasspath in Test <++= androidJars

// With this option, we cannot have dependencies in the test scope!
debugIncludesTests in Android := false

exportJars in Test := false

// Supress warnings so that Proguard will do its job.
proguardOptions in Android ++= Seq(
  "-dontwarn rx.internal.util.**",
  "-dontwarn android.test.**"
)

// Required so Proguard won't remove the actual instrumentation tests.
proguardOptions in Android ++= Seq(
  "-keep public class * extends junit.framework.TestCase",
  "-keepclassmembers class * extends junit.framework.TestCase { *; }"
)

apkbuildExcludes in Android += "LICENSE.txt"

// The next few lines will work only with sbt-scoverage version 0.99.7.1.
// Do not update until sbt-scoverage 1.0 stabilizes!

instrumentSettings

ScoverageKeys.excludedPackages in ScoverageCompile := """.*\.TR.*;.*\.TypedLayoutInflater;.*\.TypedResource;.*\.TypedViewHolder;.*\.TypedLayoutInflater"""

org.scoverage.coveralls.CoverallsPlugin.coverallsSettings

managedClasspath in ScoverageCompile <++= androidJars
