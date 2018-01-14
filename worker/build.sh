#!/bin/bash
./gradlew build
docker build -t marla/worker --build-arg JAR_FILE="./build/libs/worker-0.0.1.jar" . 
