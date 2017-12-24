### Redis

# Run
```
docker run --name redis --rm -d -p 6379:6379 redis:alpine
```

# Interact
```
nc -v --ssl localhost 6379
```
You can now issue redis commands