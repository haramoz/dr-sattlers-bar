# Steps to use docker-compose
```
cd docker-compose
docker-compose -f common.yml -f kafka_cluster.yml -f services.yml up
(-d for detached)
```
### Notes
- kafka_cluster.yml - contains 3 kafka brokers, zoo keeper
- Services.yml - Waiter, Kitchen etc services, Database (with table creation scripts)
- common.yml - sets up the bridge