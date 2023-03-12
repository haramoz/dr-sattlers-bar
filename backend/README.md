# Docker Compose
## Services
### Database
https://github.com/jdaarevalo/docker_postgres_with_data/blob/main/docker-compose.yml
Note: The port is mapped to 5438. The init scripts are only run if the database does not exist.

database name for now configured (hardcoded in service.yaml) as follows:
- username postgres
- password postgres
- db mydb

They need to come from .env file (TODO)

<pre>
sudo apt-get install postgresql-client

sudo apt install postgresql-client-common

psql -h localhost -p 5438 -U postgres -d mydb

</pre>

Aforementioned steps were needed to verify the connection can be established after docker-compose is up for the db.