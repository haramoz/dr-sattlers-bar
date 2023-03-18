# Dev notes
```
docker container inspect "id"
curl -v 172.22.0.7:5005/menu

docker exec -it a86ff3d21c07 /bin/bash
```
## Cross Origin
@CrossOrigin(origins = "*")
If you want to expose a java Springboot REST api to REACT ui then you need to annotate the REST method as follows
<pre>
@CrossOrigin(origins = "*")
@GetMapping("/findtable")
public String findTable() {...
</pre>

On the React side, we are using Axios and needs to be configured accordingly.
<pre>
const headers = {
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': process.env.ALLOW_ORIGIN || '*',
    'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept'
  };
  
export default headers;
</pre>

Then in the component ...
<pre>
    axios
      .get("http://localhost:8080/findtable", {
        headers: headers,
      })
      .then((response) => setTableNumber(response.data))
      .catch((error) => console.error(error));
</pre>
https://spring.io/guides/tutorials/rest/
https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/htmlsingle/#goals-build-image
https://github.com/sera13/docker-demo
