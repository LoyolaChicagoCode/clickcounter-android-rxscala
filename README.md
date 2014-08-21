# Learning Objectives

This example is intended as a starting point for anyone planning develop
Android applications using Scala. Its learning objectives are:

- Android application development using Scala
    - using the Simple Build Tool (sbt) for Scala
    - using IntelliJ IDEA
- Android application architecture for testability
    - [Dependency Inversion Principle (DIP)](http://en.wikipedia.org/wiki/Dependency_inversion_principle)
    - [Model-View-Adapter](http://en.wikipedia.org/wiki/Model-view-adapter) architectural pattern
    - Separation of Android activity into event-handling and lifecycle management
    - Separation of stateful GUI and reactive model using [RxScala](http://rxscala.github.io)
- Effective testing
    - Unit testing with ScalaTest
    - [Behavior-Driven Development (BDD)](http://en.wikipedia.org/wiki/Behavior-driven_development) with ScalaTest
    - [Mock objects](http://en.wikipedia.org/wiki/Mock_object) with [Mockito](http://mockito.org/)
    - Functional testing (out-of-container) using [Robolectric](http://pivotal.github.com/robolectric/)

_Anticipated FAQs are included below._

# Prerequisites

## Required

- Java Development Kit (JDK) 6 through your package management system or from
  [Oracle](http://www.oracle.com/technetwork/java/javase/downloads).
- [Android SDK](http://developer.android.com/sdk)
- [sbt](http://www.scala-sbt.org/)
- [android-sdk-plugin](https://github.com/pfn/android-sdk-plugin) for sbt
  (detailed instructions are half way down past the change log)

## Recommended

- [JetBrains IntelliJ IDEA 13.1 EAP](http://confluence.jetbrains.com/display/IDEADEV/IDEA+13.1+EAP)
- IDEA Scala plugin installed through the plugin manager

# Preparation

- Configure virtual machine hardware acceleration per
  [these instructions](http://developer.android.com/tools/devices/emulator.html#accel-vm).
- Create an Android Virtual Device (AVD) per
  [these instructions](http://developer.android.com/tools/devices). This
  device should support API level 17 (Android 4.2) and have an x86
  CPU, a skin with hardware controls, and the option _hardware
  keyboard present_ checked.
- If you have an Android device and wish to use it for development,
   you can follow
   [these instructions](http://developer.android.com/tools/device.html)
   to enable it.
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

## Running the application

Once your emulator is running or device connected, you can run the app:

    $ sbt android:install
    $ sbt android:run

The app should now start in the emulator and you should be able to interact
with it.

**Everything past this point is work in progress**

## Running the unit tests

**TODO: UPDATE**

    $ sbt test

# Developing with IntelliJ IDEA

**TODO**

## Generating the configuration files

This step requires that you have the `sbt-idea` plugin installed per the
instructions for pfn's plugin.

    $ sbt gen-idea

## Opening the project in IDEA

**TODO**

## Running the unit tests

**TODO**

## Running the application

**TODO**

The app should now start in the emulator and you should be able to interact
with it.

## Running the functional tests

**TODO: UPDATE**

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

If you are using IntelliJ IDEA, you will also need to

- rerun

        $ sbt gen-idea

- File > Refresh

- redo the preparation for running the functional tests

# Anticipated FAQs

**TODO: UPDATE**

## What if sbt reports the `R is already defined` error?

    [error] /Users/laufer/Work/VersionControl/scalaworkshop/clickcounter-android-scala/target/scala-2.10/src_managed/main/java/edu/luc/etl/cs313/android/scala/clickcounter/android/R.java:10: R is already defined as object R
    [error] public final class R {
    [error]                    ^

Simply

    $ rm -rf gen

and try again.

## What if there are unexpected errors in IDEA?

Try a combination of the following steps:

- File > Refresh
- Project > Clean
- Recreate the generated sources:

        $ sbt generate-typed-resources

If nothing helps, rerun

    $ sbt eclipse

followed by the usual steps.

## What if I get warnings referring to `ShadowGeoPoint` or similar during the Robolectric tests?

    Warning: an error occurred while binding shadow class: ShadowGeoPoint

These warnings are harmless, but if you are developing location-based apps,
then you will need to add the corresponding `maps.jar` to the build path as an
external jar. For example, for API level 17, you would find this below your
Android SDK installation folder:

    android-sdks/add-ons/addon-google_apis-google-17/libs/maps.jar

## Why does my app work when I run it through Eclipse but force-close when I run it through sbt?

We are not sure but believe that the AndroidProguardScala plugin enables
Eclipse to create a better apk (application package) than sbt on its own.

## Why don't you cover in-container functional testing?

Francois-Xavier Thomas's
[giter8 template](http://github.com/fxthomas/android-app.g8) does generate
a sample in-container functional test based on
[ActivityInstrumentationTestCase2](http://developer.android.com/reference/android/test/ActivityInstrumentationTestCase2.html),
but this stopped working for me once my activity became more complex (by mixing
in traits etc.). I have given up on this type of testing for now and have been
testing with Robolectric as an alternative.

## Why don't you provide a giter8 template?

Because this work is preliminary and I want it to stabilize before turning
it into a template. And there are some manual steps that still need to be
automated.

## What if I want to start from scratch?

**TODO**

# Acknowledgments

An earlier version of this example is based on Francois-Xavier Thomas's
[giter8 template](http://github.com/fxthomas/android-app.g8), which
builds on Jan Berkel's [sbt plugin](https://github.com/jberkel/android-plugin).
