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

    $ emulator -avd YourAVD &

It will take the emulator a couple of minutes to boot to your AVD's home or
lock scren. If you set up hardware acceleration correctly, you will see

    HAX is working and emulator runs in fast virt mode

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

## Running the application

    $ sbt android:package-debug
    $ sbt android:start-emulator

The app should now start in the emulator and you should be able to interact
with it.

# Developing with Eclipse

## Generating the configuration files

    $ sbt eclipse

## Import into Eclipse

- File > Import > General > Existing Projects into Workspace

If you see a popup referring to a rebuild error, it is harmless, and you can
dismiss it.

## Running the unit tests

- Expand project root > eclipse
- Right-click scalatest.launch > Run As > scalatest

You will see console output as well as a test runner GUI for ScalaTest.

## Running the application

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

# Adding dependencies

To add a dependency, you can usually

- look it up by name in [MVNrepository](http://mvnrepository.com/)
- find the desired version
- select the sbt tab
- copy the portion _after_ `libraryDependencies +=`
- paste it into this section of project/Build.scala (followed by a comma)

    libraryDependencies ++= Seq(

If you are using Eclipse, you will also need to

- rerun

    $ sbt eclipse

- File > Refresh

- redo the preparation for running the functional tests

# Anticipated FAQs



# Acknowledgments

This example is based on Francois-Xavier Thomas's
[giter8 template](github.com/fxthomas/android-app.g8), which
builds on Jan Berkel's [sbt plugin](https://github.com/jberkel/android-plugin).