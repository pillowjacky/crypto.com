# Crypto.com Coding Challenge

## Feature Checklist
- Deposit into user wallet ✅
- Withdraw money from user wallet ✅
- Send money to another user ✅
- Check user wallet balance ✅
- Exchange money to another currency ✅
- Check Transaction history ✅

## Assumption && Scoping
In this implementation, to keep it simple...
- Assumming all incoming data is valid, so validation logic is not included
- Number rounding is not cared
- Receive currency of a send money / money transfer transaction is included in the sender request, but it should be decided by receiver in real cases IMO 
- Only part of the integration test is included (across service & repository layer, with H2 in memory database), isolated unit test, more details integrations test, load test...etc. should be implement in real cases

## Decision && Future Improvement
- URL is not strictly following RESTful style, as some action could be quite ambiguous
- For a more complete system, we should have a authentication part, and if we are using token based authentication like JWT, we could remove custome/{id} part from some URL, as we could know the user identity from the token
- This implementation is using CQRS/Event Sourcing design, the characteristic of CQRS/ES is very suitable for this type of application, especially on wallet and transacion, for example:
    - Independent scaling for read write operation
    - Seperation of concern
    - Command-query separation
    - Audit Trail
    - Time-Travelling for debugging and system state
    - ...etc.
- This implementation is splitted into different module (package), to prepare for the possibility to split into microservice in the future, and microservices could help the CQRS/ES design to be more complete, for example we could:
    - introduce event-driven, async design
    - generate projection / data snapshot by event, instead of the handling right now (insert into event / snapshot table in a method call)
    - also we could gain the other benefit from microservices architecture itself
- Proper logging should be introduce to improve traceability and maintainability
- For better API documentation, we could introduce Swagger

## How to setup and run my code?

- Make sure this machine is installed docker runtime
- Open project root directory in terminal
- Grant execute permission for mvnm if not have
- run command: make clean-build && make run
- You are good to go :)

> Remark 1: there are some basic testing data already put into db, using flyway migration, those scripts are stay in src/main/resources/db/migration

> Remark 2: there are some cURL call I wrote for my own testing, those scripts are stay in src/test/resources/scripts if you are interested