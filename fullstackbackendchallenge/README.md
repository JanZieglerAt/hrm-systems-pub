# Fullstack backend challenge

### Implementation of a REST-WS backend.

The following web services are implemented:
* Add persons
* Get persons
* Update persons
* Get statistics

##### All data is cumulated up to a restart of the backend.

Statistics contains:

per usecase
* number of requests
* number of valid requests
* number of invalid requests (grouped)

generally
* a total number of persons

### Build and run
* Java 17 and Maven must be installed.

* Running from IDE
* Running as a JAR ``` $java -jar target/fullstackbackendchallenge-0.0.1-SNAPSHOT.jar ``` (maven build first)
* Using the Maven plugin ``` $mvn spring-boot:run ```

### Postman collection with the required REST services calls
* ``` fullstack-backend-challenge.postman_collection.json```
