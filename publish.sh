#!/bin/bash

if [ $# -eq 0 ]; then
    echo "No arguments supplied"
    exit
fi
VERSION=${1}

REPO=andrearegistry.azurecr.io

MODULES=("api" "frontend" "websocket" "worker")
for MODULE in "${MODULES[@]}"; do
    docker tag marla/${MODULE} ${REPO}/marla/${MODULE}:${VERSION}
    docker push ${REPO}/marla/${MODULE}:${VERSION}
done