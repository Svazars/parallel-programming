#!/usr/bin/env bash

set -euo pipefail

export IMAGE_NAME=svazars/parallel-programming-tools:jpf

# Step 0: ensure you have up-to-date docker image with jpf
#         up-to-date means "it was checked against homeworks by lecturer", it does not mean "latest release from official repo"
docker pull $IMAGE_NAME

# Step 1: ensure your class (system-under-test, SUT) is compiled with compatible javac (as of march 2026, JPF accepts jdk11 only)
docker run \
 -v $(pwd):/workspace \
 -w /workspace \
 -it --rm $IMAGE_NAME javac Task13.java

# Step 2: use Java Path Finder (JPF) to analyze your program using bounded model checking under interleaving execution model
#         JVM_FLAGS is a (temporary?) workaround to https://github.com/javapathfinder/jpf-core/issues/107
docker run \
 -v $(pwd):/workspace \
 -w /workspace \
 -e JVM_FLAGS="-ea -Xmx1g --add-opens java.base/jdk.internal.misc=ALL-UNNAMED" \
 -it --rm $IMAGE_NAME jpf Task13.jpf
