# RestSpringMvcJpaApp

[![Build Status](https://travis-ci.org/fdlessard/RestSpringMvcJpaProject.svg)](https://travis-ci.org/fdlessard/RestSpringMvcJpaProject)
[![License](http://img.shields.io/:license-mit-blue.svg)](https://github.com/fdlessard/RestSpringMvcJpaProject/blob/master/LICENSE)

TODO

Run the tests:
===

$ mvn test


Start the tomcat application server:
===

mvn clean install tomcat7:run

mvn clean install jetty:run


Tomcat vm args (for instrumentation)
====================================

You need to put this in the vmargs for the webserver

-javaagent:/.../.../.m2/repository/org/springframework/spring-instrument/4.3.1.RELEASE/spring-instrument-4.3.1.RELEASE.jar -noverify


Url of the Application:
===

http://localhost:8080/RestSpringMvcJpaApp/hello

http://localhost:8080/RestSpringMvcJpaApp/salesoders

