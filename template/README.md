Prototype Web Application
=========================

A Java web application built with the Spring 5 platform.
It contains the following features:

* A service layer for managing model objects.
* A REST layer for exposing model objects.
* Multi module project based on Maven.

Build instructions
------------------
```
mvn clean install
```

Run instructions
----------------
```
cd template-ui
mvn spring-boot:run
```

After the project artifacts are deployed you can

Access the REST API through:
http://localhost:8080/api/entities

Access the UI at:
http://localhost:8080/
