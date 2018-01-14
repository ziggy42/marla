#!/bin/bash

MODULES=("api" "frontend" "websocket" "worker")
for MODULE in "${MODULES[@]}"; do
    echo Building ${MODULE} Docker image
    cd ${MODULE} && ./build.sh && cd ..
done