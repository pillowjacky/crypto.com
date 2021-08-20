FROM alpine:latest

RUN apk update \
    && apk upgrade \
    && apk add openjdk11

WORKDIR /app
COPY ./target/challenge-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["java","-jar","challenge-0.0.1-SNAPSHOT.jar"]
