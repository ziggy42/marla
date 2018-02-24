#!/bin/bash

MODULE=frontend
REPO=andrearegistry.azurecr.io

if [ $# -eq 0 ]; then
    echo "No arguments supplied"
    exit
fi
VERSION=${1}

docker build -t marla/${MODULE} .
docker tag marla/${MODULE} ${REPO}/marla/${MODULE}:${VERSION}
docker push ${REPO}/marla/${MODULE}:${VERSION}
