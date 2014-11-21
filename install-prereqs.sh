#!/bin/sh

# Installs the required prerequisites for an sbt-based Android build.
# Designed for use with Travis-CI, but can also be used locally
# (assuming JDK and sbt are already installed).

# install required Ubuntu packages

sudo apt-get update -qq
sudo apt-get install -qq lib32stdc++6 lib32z1

# manually install Android SDK components

wget http://dl.google.com/android/android-sdk_r${ANDROID_SDK_VERSION}-linux.tgz
tar -zxf android-sdk_r${ANDROID_SDK_VERSION}-linux.tgz
echo yes | ${ANDROID_HOME}/tools/android update sdk --filter tools,platform-tools,build-tools-${ANDROID_BUILD_TOOLS_VERSION},android-${ANDROID_API_LEVEL},extra-android-support,extra-android-m2repository,extra-google-m2repository --no-ui --force --no-https --all > /dev/null
