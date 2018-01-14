#!/bin/bash
./gradlew build
docker build -t marla/api --build-arg JAR_FILE="./build/libs/api-0.0.1.jar" . 
