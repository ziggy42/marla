#!/bin/bash

kubectl create -f kubernetes/redis-deployment.yaml && kubectl create -f kubernetes/redis-service.yaml
kubectl create -f kubernetes/api-deployment.yaml && kubectl create -f kubernetes/api-service.yaml
kubectl create -f kubernetes/worker-deployment.yaml && kubectl create -f kubernetes/worker-service.yaml
