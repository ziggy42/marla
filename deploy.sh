#!/bin/bash
kubectl apply -f <(istioctl kube-inject -f marla.yaml)
