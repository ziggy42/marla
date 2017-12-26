#!/bin/bash

function request {
	curl --request POST \
	  --url http://localhost:8080/api/job \
	  --header 'content-type: application/json' \
	  --data '{"clientId": "Andrea","script": "let fib = fn (n) {if (n == 0) {return 1;}if (n == 1) {return 1; } return fib(n - 1) + fib(n - 2); }; fib(30)"}'
}

COUNTER=0
while true; do 
	echo Request ${COUNTER}
	request
	((COUNTER++))
done