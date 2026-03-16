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
 -it --rm $IMAGE_NAME javac Task12.java

# Step 2: use Java Path Finder (JPF) to analyze your program using bounded model checking under interleaving execution model
docker run \
 -v $(pwd):/workspace \
 -w /workspace \
 -it --rm $IMAGE_NAME jpf Task12.jpf
