# Introduction
This is a REST API written in GO and GIN. The APIs are annotated and Swaggo was used to generate swagger contracts. This project supports REST based apis for billing and payment

![Swagger Contracts](payment-go-apis.png)

## Documentation 
Swaggo is used, it helps generate swagger definition from Annotated GO Apis directly.
### Repository:
https://github.com/swaggo/swag

### Install if required
```go install github.com/swaggo/swag/cmd/swag@latest```
### To generate documentation
```swag init```

this would generated docs/docs.go, docs/swagger.json and docs/swagger.yaml

Go to https://editor.swagger.io/
paste the docs/swagger.yml to visualize the current api definitions, generated using swaggo, based on YOUR annotations of the API