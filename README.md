Developer's test exercise solution
-------------------------

## Installation

* Install postgres and add DB url, database name, user and password to application.properties and pom.xml
* To import database run: mvn sql:execute && mvn flyway:baseline && mvn flyway:migrate
* Run application with command: mvn install && mvn spring-boot:run
* When application is up and running, grades can be accessed from url: http://localhost:8080/

## Estimated time spent on a task
* Learning AngularJS - 1h
* Learning flyway & setup - 0.5h
* Postgres setup - 0.2h
* REST API development - 3h
* UI development - 2h
* Unit tests - 1.5h
* Manual testing - 0.5h
* Other - 0.3h

Total: 9h

## Todo
What I would add, If I would have more time.
* Pagination and sorting support to REST API and UI
* Error message structure to REST API
* More and better exceptions and their descriptions
* Annotations to generate REST documentation (swagger)
* Better logging (all requests/responses)
* UI tests
* Authentication support
* Docker support (I was not sure if the db should be added to the image or not, as I have little experience with docker)

## Comments
* I did not comment methods or classes, as the method names, spring annotations and variable names are self explanatory and explain quite well what the code should and is doing