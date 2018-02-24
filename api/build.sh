#!/bin/bash

MODULE=api
REPO=andrearegistry.azurecr.io

if [ $# -eq 0 ]; then
    echo "No arguments supplied"
    exit
fi
VERSION=${1}

./gradlew build

docker build -t marla/${MODULE} --build-arg JAR_FILE="./build/libs/${MODULE}-0.0.1.jar" .
docker tag marla/${MODULE} ${REPO}/marla/${MODULE}:${VERSION}
docker push ${REPO}/marla/${MODULE}:${VERSION}
