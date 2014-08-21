import android.Keys._

android.Plugin.androidBuild

name := "clickcounter-android-rxscala"

version := "0.2.1"

scalacOptions in Compile += "-feature"

platformTarget in Android := "android-19"

libraryDependencies ++= Seq(
  "com.novocode" % "junit-interface" % "0.11" % "test",
  "org.scalatest" % "scalatest_2.10" % "2.2.1" % "test",
  "org.robolectric" % "robolectric" % "2.3" % "test",
  "org.mockito" % "mockito-core" % "1.9.5" % "test",
  "com.netflix.rxjava" % "rxjava-core" % "0.20.0-RC6",
  "com.netflix.rxjava" % "rxjava-scala" % "0.20.0-RC6",
  "com.netflix.rxjava" % "rxjava-android" % "0.20.0-RC6"
)

proguardOptions in Android += "-dontwarn rx.internal.util.**"