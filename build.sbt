import android.Keys._

android.Plugin.androidBuild

name := "clickcounter-android-rxscala"

version := "0.2.1"

scalacOptions in Compile += "-feature"

platformTarget in Android := "android-19"

libraryDependencies ++= Seq(
  "com.netflix.rxjava" % "rxjava-core" % "0.20.3",
  "com.netflix.rxjava" % "rxjava-scala" % "0.20.4",
  "com.netflix.rxjava" % "rxjava-android" % "0.20.4"
)

proguardOptions in Android += "-dontwarn rx.internal.util.**"
