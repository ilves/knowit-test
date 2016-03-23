Developer's test exercise
-------------------------
Knowit has developed an imaginary Student Information System for Knowit's University. It consists of JAVA 8 restful API and AngularJS frontend. You can download the code  from Bitbucket repository. 

Your task is to enhance the API and frontend code. The main tasks are following:

1. The API must calculate and frontend show the weighted average grade  of student grades.
2. The user can enter new grades for students from the frontend, sent to the API and saved to the database.

Additional tasks, if you would like to earn extra credits:

1. The user can delete grades for students from the frontend, sent to the API and deleted from the database.
2. The user can edit grades for students from the frontend, sent to the API and deleted from the database.
3. Package the software with docker.
4. Add the database change migration library.

You will be evaluated based on the following criteria:

* Semantics
* Logics
* Written unit tests
* Included documentation
* Repository usage and best practice

Choose your methods, present the outcomes and report us, how much time it took by task. Be prepared to present your work in 10 minutes to the live audience.


## Java Sample Exercise Grades Service

* Install postgres and add DB user and pass to application.properties
* Import database from knowit_challenge.sql file
* Run application with command: mvn install && mvn spring-boot:run
* When application is up and running, grades can be accessed from url: http://localhost:8080/grades

All the copyright of the code goes to Knowit Estonia OÜ after submitting the code to Knowit e-mail.