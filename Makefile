.PHONY: clean-build
clean-build:
	./mvnw clean
	./mvnw package -Dmaven.test.skip=true
	docker-compose build

.PHONY: run
run:
	docker-compose down
	docker-compose up
