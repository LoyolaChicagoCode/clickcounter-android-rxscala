#!/bin/sh

# Installs the required prerequisites for an sbt-based Android build.
# Designed for use with Travis-CI, but can also be used locally
# (assuming JDK and sbt are already installed).

# install required Ubuntu packages

sudo apt-get update -qq
sudo apt-get install -qq lib32stdc++6 lib32z1

# manually install Android SDK components

export ANDROID_SDK_VERSION=23.0.2
export ANDROID_HOME=$PWD/android-sdk-linux
export PATH=${PATH}:${ANDROID_HOME}/tools:${ANDROID_HOME}/platform-tools

wget http://dl.google.com/android/android-sdk_r${ANDROID_SDK_VERSION}-linux.tgz
tar -zxf android-sdk_r${ANDROID_SDK_VERSION}-linux.tgz
echo yes | android update sdk --filter tools,platform-tools,build-tools-21.1.1,android-19,extra-android-support,extra-android-m2repository,extra-google-m2repository --no-ui --force --no-https --all > /dev/null

# create local.properties for Android build system to find SDK

echo sdk.dir=${ANDROID_HOME} > local.properties
