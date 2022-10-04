# dr-sattlers-bar
Microservices for serving cocktails

System Design is in progress:

![System Design](dr-sattlers-bar-design.drawio.png)

## Ui
This is the web interface. Following APIs are available:
1) Read menu (Get menu/cocktails) (Get menu/cocktailid)
2) Order (post order/cocktailid)
3) Check order status (get status/cocktailid)
4) GraphQl isAlcoholic?
5) Pay (Post pay/billid)

6) Metrics (admin)
7) Notifications (admin)

### Routes
bar, metrics, notifications


## Backend
Some microservices written in node js, some in Java and some in python/go?

## Events

Order placed
order ready
payment received
payment failed

https://www.confluent.io/blog/spring-for-apache-kafka-deep-dive-part-2-apache-kafka-spring-cloud-stream/

https://www.confluent.io/blog/spring-for-apache-kafka-deep-dive-part-1-error-handling-message-conversion-transaction-support/

## List of microservices

## Actors
https://github.com/topics/actor-model

## Dockerize

## Keycloak

docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:19.0.2 start-dev


https://blog.logrocket.com/implement-keycloak-authentication-react/

Later on integration with Samba/openldap ?

https://www.talkingquickly.co.uk/keycloak-and-openldap-on-kubernetes

https://cagline.medium.com/authenticate-and-authorize-react-routes-component-with-keycloak-666e85662636

TO DO:
Login page and oauth
Routing
roll based access to tabs





