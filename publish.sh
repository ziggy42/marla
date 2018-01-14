#!/bin/bash

MODULES=("api" "frontend" "websocket" "worker")
for MODULE in "${MODULES[@]}"; do
    docker tag marla/${MODULE} repo.treescale.com/ziggy42/marla/${MODULE}
    docker push repo.treescale.com/ziggy42/marla/${MODULE}
done