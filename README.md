# marla

![marla](https://www.inmediarex.it/wp-content/uploads/2017/02/fight-club_helena-bonham-carter-hat-front-marla-singer-is-not-real.jpg)

## Run on k8s
```
kubectl apply -f <(istioctl kube-inject -f marla.yaml)
```

## Test locally
```
kubectl port-forward websocket-2566111427-729q1 8090:8100
```

```
kubectl port-forward frontend-3286222880-0v2t7 3000
```