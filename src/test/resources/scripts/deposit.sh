curl -v -X POST -H 'Content-Type: application/json' -d '{"transactionType":"DEPOSIT","customerId":1,"currency":"HKD","amount":200}' http://localhost:8080/customer/1/deposit