# find-north-country-name-by-ip
Spring boot application example
The project is based on a small web service which uses the following technologies:

Java 11

Spring MVC with Spring Boot

Maven

The architecture of the web service is built with the following components:

DataTransferObjects: Objects which are used for outside communication via the API
Controller: Implements the processing logic of the web service, parsing of parameters and validation of in- and outputs.
Service: Implements the business logic and handles the access to the DataAccessObjects.
How to start the app
You should be able to start the example application by executing com.example.demo.GeolocationApplication, which starts a webserver on port 8888 (http://localhost:8888).

Useful commands

curl -u user:password "http://localhost:8888/northCountries"
