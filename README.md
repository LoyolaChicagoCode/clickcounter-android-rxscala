# Learning Objectives

This example is intended as a starting point for anyone planning develop
*reactive* Android applications using Scala. Its learning objectives are:

- Android application development using Scala
    - using the Simple Build Tool (sbt) for Scala in conjunction with 
      [pfn's well-maintained plugin](https://github.com/pfn/android-sdk-plugin)
    - using IntelliJ IDEA
- Android application architecture for testability and maintainability
    - [Dependency Inversion Principle (DIP)](http://en.wikipedia.org/wiki/Dependency_inversion_principle)
    - [Model-View-Adapter](http://en.wikipedia.org/wiki/Model-view-adapter) architectural pattern
    - Separation of Android activity into event-handling and lifecycle management
    - Separation of stateful and reactive components using [RxScala](http://rxscala.github.io)
- Effective testing
    - Unit testing and [Behavior-Driven Development (BDD)](http://en.wikipedia.org/wiki/Behavior-driven_development) 
      with ScalaTest
    - [Mock objects](http://en.wikipedia.org/wiki/Mock_object) with [ScalaMock](http://scalamock.org/)
    - Functional testing (out-of-container) using [Robolectric](http://pivotal.github.com/robolectric/)

# Prerequisites

## Required

- Java Development Kit (JDK) 6 through your package management system or from
  [Oracle](http://www.oracle.com/technetwork/java/javase/downloads).
- [Android SDK](http://developer.android.com/sdk)
- [sbt](http://www.scala-sbt.org/)
- [android-sdk-plugin](https://github.com/pfn/android-sdk-plugin) for sbt
  (detailed instructions are half way down past the change log)

## Recommended

- [JetBrains IntelliJ IDEA 13.1 CE](http://www.jetbrains.com/idea)
- IDEA Scala plugin installed through the plugin manager

# Preparation

- Configure virtual machine hardware acceleration per
  [these instructions](http://developer.android.com/tools/devices/emulator.html#accel-vm).
- Create an Android Virtual Device (AVD) per
  [these instructions](http://developer.android.com/tools/devices). This
  device should support API level 19 (Android 4.4.2) and have an x86
  CPU, a skin with hardware controls, and the option _hardware
  keyboard present_ checked.
- If you have an Android device and wish to use it for development,
   you can follow
   [these instructions](http://developer.android.com/tools/device.html)
   to enable it.
- Check out this project using Mercurial (hg) or download
  [this zip file](https://bitbucket.org/loyolachicagocs_plsystems/clickcounter-android-rxscala/get/default.zip).

# Developing on the Command-line

These instructions assume that `$ANDROID_HOME/tools` and 
`$ANDROID_HOME/platform-tools` are in the `$PATH`.      

## Starting the emulator

To start the emulator:

    $ emulator -avd YourAVD &

It will take the emulator a couple of minutes to boot to your AVD's home or
lock screen. If you set up hardware acceleration correctly, you will see

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

## Specifying the location of the Android SDK

You can either

- set `$ANDROID_HOME` to the directory where you installed your 
  Android SDK

- create a file `local.properties` in your project root 
  (or copy an existing one) with a single line
  
        sdk.dir=/location/of/android/sdk

## Running the application

Once your emulator is running or device connected, you can run the app:

    $ sbt android:run

The app should now start in the emulator and you should be able to interact
with it.

## Running the tests

This command runs the unit tests and the Robolectric-based out-of-container
functional tests.

    $ sbt test

# Developing with IntelliJ IDEA

## Generating the configuration files

This step requires that you have the `sbt-idea` plugin installed per the
instructions for pfn's plugin.

    $ sbt gen-idea
    
You will have to repeat this step after every change to the `build.sbt` file 
(see also under "adding dependencies" below.

## Opening the project in IDEA

Open *(not import)* the project through the initial dialog or `File > Open`.
You should now be able to edit the project with proper syntax-directed
editing and code completion.

Right after opening the project, you may be asked to confirm the location of
the Android manifest file.

## Running the tests and the application

Some aspects of generated IDEA Android/Scala project do not work out of the box.
We have found it easier to open a terminal within IDEA using 
`View > Tool Windows > Terminal` and running `sbt test` or `sbt android:run`
as desired. In the latter case, the app should start in the emulator and 
you should be able to interact with it.

# Adding dependencies

To add a dependency, you can usually

- look it up by name in the [Central Repository](http://search.maven.org) 
  or [MVNrepository](http://mvnrepository.com/)
- find the desired version (usually the latest released or stable version)
- select the sbt tab
- copy the portion _after_ `libraryDependencies +=`
- paste it into this section of `build.sbt` (followed by a comma)

        libraryDependencies ++= Seq(

If you are using IntelliJ IDEA, you will also need to

- rerun

        $ sbt gen-idea

- back in IDEA, confirm that you want to reload the project
 
- reconfirm the location of the Android manifest file

# Acknowledgments

An earlier version of this example is based on Francois-Xavier Thomas's
[giter8 template](http://github.com/fxthomas/android-app.g8), which
builds on Jan Berkel's [sbt plugin](https://github.com/jberkel/android-plugin).
