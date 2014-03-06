// Include the Android plugin
androidDefaults

// Name of your app
name := "clickcounter-android-scala"

// Version of your app
version := "0.2.0"

// Version number of your app
versionCode := 0

// Version of Scala
scalaVersion := "2.10.3"

// Version of the Android platform SDK
platformName := "android-17"

libraryDependencies ++= Seq(
  "com.novocode" % "junit-interface" % "0.10" % "test",
  "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test",
  "org.robolectric" % "robolectric" % "2.2" % "test",
  "org.mockito" % "mockito-core" % "1.9.5" % "test"
)
