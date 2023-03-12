Next goal:

Microservices spring boot 

Waiter

POST takeOrders
GET menu
POST payment
GET estimatedTimeToServeFood
GET suggestFood

GET serveFood

Events:

Produces:

Order placed
Order served
Payment initiated


Consumes:

Order Ready
payment success
payment failed

Namespace:
com.dr.sattlers.bar.employee.waiter
com.dr.sattlers.bar.infra.kafka
com.dr.sattlers.bar.infra.config

Refs:
https://techdozo.dev/restful-microservices-with-spring-boot-and-kubernetes/
https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api

after this is available, will write a node js backend to interact with this one, I think should a separate repository.

29 Oct
REST Methods are in place, we need to Dockerize the app and test it, Write test cases. Introduce Swagger contract.

Implemented the payment API with go and gin.
https://go.dev/doc/tutorial/web-service-gin

curl http://localhost:8080/payment/ --include --header "Content-Type: application/json" --request "POST" --data '{"tableid":"3","method":"credit"}'

https://github.com/go-swagger/go-swagger

Doing Kafka experiment
https://www.baeldung.com/ops/kafka-docker-setup - > Could be useful for the Dockerizing step  

11 March 2023:
While integrating the UI with Java and golang backend felt the neeed to create separate node-backend module, which is sitting inside the frontend parent directory parallel with UI folder which contains the react codes.

- node-backend will have its own Dockerfile
- ui will have its own Dockerfile
- will eventually have to combine both in a docker-dompose file -> HELM? 