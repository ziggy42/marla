#!/bin/bash
./gradlew build
docker build -t marla/websocket --build-arg JAR_FILE="./build/libs/websocket-1.0-SNAPSHOT.jar" . 
