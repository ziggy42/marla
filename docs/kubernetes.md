# Kubernetes

Stat local Kubernetes cluster
```
minikube start
```

Create a secret to be able to pull the images from the private repo
```
kubectl create -f kubernetes/secret.yaml
```

```
kubectl create -f kubernetes/redis-deployment.yaml && kubectl create -f kubernetes/redis-service.yaml

kubectl create -f kubernetes/api-deployment.yaml && kubectl create -f kubernetes/api-service.yaml

kubectl create -f kubernetes/worker-deployment.yaml && kubectl create -f kubernetes/worker-service.yaml
```

### Get available endpoints
```
minikube service list
```