# Learning Objectives

This example is intended as a starting point for anyone planning develop
Android applications using Scala. Its learning objectives are:

- Android application development using Scala
  - using the Simple Build Tool (sbt) for Scala
  - using Eclipse
- Android application architecture for testability
  - [Dependency Inversion Principle (DIP)](http://en.wikipedia.org/wiki/Dependency_inversion_principle)
  - [Model-View-Adapter](http://en.wikipedia.org/wiki/Model-view-adapter) architectural pattern
  - Separation of Android activity into event-handling and lifecycle management
- Effective testing
  - Unit testing with ScalaTest
  - [Behavior-Driven Development (BDD)](http://en.wikipedia.org/wiki/Behavior-driven_development) with ScalaTest
  - [Mock objects](http://en.wikipedia.org/wiki/Mock_object) with [Mockito](http://mockito.org/)
  - Functional testing (out-of-container) using [Robolectric](http://pivotal.github.com/robolectric/)

# Prerequisites

## Required

- Java Development Kit (JDK) 6 through your package management system or from
  [Oracle](http://www.oracle.com/technetwork/java/javase/downloads).
  ([ProGuard](http://proguard.sourceforge.net/), used for deploying Scala
  apps, does not yet support Java 7!)
- [sbt](http://www.scala-sbt.org/)
- [Android SDK](http://developer.android.com/sdk)

## Optional

- [Eclipse 4.2.x IDE for Java Developers](http://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/junosr1)
- [Android ADT plugin](http://developer.android.com/tools/sdk/eclipse-adt.html)
- [Scala IDE Eclipse plugin](http://scala-ide.org/download/milestone.html#scala_ide_21_milestone_3) corresponding to your Eclipse version

# Preparation

- Configure virtual machine hardware acceleration per
  [these instructions](http://developer.android.com/tools/devices/emulator.html#accel-vm).
- Create an Android Virtual Device (AVD) per
  [these instructions](http://developer.android.com/tools/devices). This
  device should support API level 17 (Android 4.2) and have an x86 CPU.
- Check out this project using Mercurial (hg) or download
  [this zip file](https://bitbucket.org/loyolachicagocs_plsystems/clickcounter-android-scala/get/default.zip).

# Developing on the Command-line

## Starting the emulator

To start the emulator:

    $ sbt android:emulator-start YourAVD

It will take the emulator a couple of minutes to boot to your AVD's home or
lock scren.

To verify that you have a connection with the emulator:

    $ adb devices

The resulting list should look like this:

    List of devices attached
    emulator-5554   device

If this is not the case, restart the adb server

    $ adb kill-server
    $ adb start-server

and check again.

## Running the unit tests

    $ sbt test

## Running the sample application

    $ sbt android:package-debug
    $ sbt android:start-emulator

The app should now start in the emulator and you should be able to interact
with it.

# Developing with Eclipse

## Generating the configuration files

    $ sbt eclipse

## Import into Eclipse

- File > Import > General > Existing Projects into Workspace

## Running the unit tests

- Expand project root > eclipse
- Right-click scalatest.launch > Run As > scalatest

You will see console output as well as a test runner GUI for ScalaTest.

## Running the sample application

- Right-click project root > Run As > Android Application

The app should now start in the emulator and you should be able to interact
with it.

## Running the functional tests

You will need to perform the following steps before you can run
the Robolectric-based out-of-container functional tests:

- Run this command in the project root directory to create `local.properties`
  so Robolectric can find the Android SDK. You don't need to worry about the
  two other generated files.

        $ android update project -p .

- Back in Eclipse, right-click project root > Build Path > Configure Build Path >
  Order and Export, select android.jar, press Bottom, and then OK.

- Remove the `@Ignore` annotation from the test itself.

Now you should be able to run the functional tests like this:

- Navigate to project root > eclipse
- Right-click robolectric.launch > Run As > robolectric

These should run (and pass) in the standard JUnit test runner.

# Acknowledgments

This example is based on Francois-Xavier Thomas's
[giter8 template](github.com/fxthomas/android-app.g8), which
builds on Jan Berkel's [sbt plugin](https://github.com/jberkel/android-plugin).