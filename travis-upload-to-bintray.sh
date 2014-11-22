#!/bin/sh

PROJECT_VERSION=$(TERM=dumb sbt 'show version' | tail -n 1 | cut -c8- -) && \
BINTRAY_VERSION=${PROJECT_VERSION}.${TRAVIS_BUILD_NUMBER} && \
./travis-bintray-credentials.sh && \
curl --config $HOME/.curlrc -H "Content-Type: application/json" -d "{ \"name\": \"${BINTRAY_VERSION}\" }" https://bintray.com/api/v1/packages/lucoodevcourse/generic/clickcounter-android-rxscala/versions

#       &&
#      sbt android:package &&
#      curl -T target/android-bin/clickcounter-android-rxscala-debug.apk 'https://bintray.com/api/v1/content/lucoodevcourse/generic/clickcounter-android-rxscala/${BINTRAY_VERSION}/clickcounter-android-rxscala-${BINTRAY_VERSION}.apk;publish=1'
