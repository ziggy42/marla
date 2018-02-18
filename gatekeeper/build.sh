#!/bin/bash
./gradlew build
docker build -t marla/gatekeeper --build-arg JAR_FILE="./build/libs/gatekeeper-0.0.1.jar" .
