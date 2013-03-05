#Learning Objectives

- Familiarity with a simple Android project using Scala with sbt (and optionally Eclipse)

#Prerequisites

## Required

- Java Development Kit (JDK) 6 through your package management system or from
  [Oracle](http://www.oracle.com/technetwork/java/javase/downloads).
  ([ProGuard](http://proguard.sourceforge.net/) does not yet support Java 7!)
- [sbt](http://www.scala-sbt.org/)
- [Android ADK](http://developer.android.com/sdk)

## Optional

- [Eclipse 4.2.x IDE for Java Developers](http://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/junosr1)
- [Android ADT plugin](http://developer.android.com/tools/sdk/eclipse-adt.html)
- [Scala IDE Eclipse plugin](http://scala-ide.org/download/milestone.html#scala_ide_21_milestone_3) corresponding to your Eclipse version

# Command-line

## Starting the emulator

    $ sbt android:emulator-start

## Running the sample application

    $ sbt android:start-emulator

# Eclipse

## Generating the configuration files

    $ sbt eclipse

## Import into Eclipse

- File > Import > General > Existing Projects into Workspace

## Running the sample application

- right-click project root > Run As > Android Application

## Regenerate the generated Java files

- On the command-line:

        $ sbt generate-typed-resources

- In Eclipse, refresh the project.