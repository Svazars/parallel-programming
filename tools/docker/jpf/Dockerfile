# gradle 8.4 used in jpf-core as of 15 march 2026
# jdk11 is the latest supported java version as of 15 march 2026
FROM gradle:8-jdk11-jammy

ARG GIT_REPO="https://github.com/javapathfinder/jpf-core.git"
ARG GIT_BRANCH="master"
# latest commit as of 15 march 2026
ARG GIT_COMMIT="f70927c"
# JPF tests tend to consume a lot of RAM, so your system could fall into "unresponsive swap" spiral of death
ARG PARALLELISM=4 

RUN mkdir -p /home && \
  cd /home && \
  git clone $GIT_REPO jpf-core --branch $GIT_BRANCH && \
  cd jpf-core && \
  git checkout $GIT_COMMIT

WORKDIR /home/jpf-core

RUN gradle clean build --max-workers=$PARALLELISM --no-daemon

ENV PATH="$PATH:/home/jpf-core/bin"
