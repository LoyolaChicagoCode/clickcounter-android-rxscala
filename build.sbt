import android.Keys._

android.Plugin.androidBuild

name := "clickcounter-android-scala"

version := "0.2.0"

scalacOptions in Compile += "-feature"

platformTarget in Android := "android-17"

libraryDependencies ++= Seq(
  "com.novocode" % "junit-interface" % "0.10" % "test",
  "org.scalatest" % "scalatest_2.10" % "2.1.0" % "test",
  "org.robolectric" % "robolectric" % "2.2" % "test",
  "org.mockito" % "mockito-core" % "1.9.5" % "test"
)
