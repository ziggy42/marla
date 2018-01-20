#!/bin/bash

if [ $# -eq 0 ]; then
    echo "No arguments supplied"
    exit
fi
VERSION=${1}

MODULES=("api" "frontend" "websocket" "worker")
for MODULE in "${MODULES[@]}"; do
    docker tag marla/${MODULE} repo.treescale.com/ziggy42/marla/${MODULE}:${VERSION}
    docker push repo.treescale.com/ziggy42/marla/${MODULE}:${VERSION}
done