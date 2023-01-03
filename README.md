
# Getting Started

Rest-API for the electric vehicle charging station management system
Assumptions and Instructions to build and run the project




## Assumptions

- For this task I have assumed 1 degree = 111 KM for spatial distance calculation
- Etags and more validation can be implemented but skipped because of limited time

## Prerequisites

To run this project, you will need to setup the following

- JDK 8 or newer
- Maven
- Postgres
- PostGIS


## DB setup

- Create a db with name "evsms"
- update username and password in application.yml 
- Enable PostGIS extension
```bash
  CREATE EXTENSION postgis
```



## Build & Run 

Build project

```bash
  mvn clean package
```

Run test

```bash
  mvn test
```

Run integration test (make sure DB is setup)

```bash
  mvn clean verify
```

Start the application

```bash
  mvn spring-boot:run
```

## Demo

Application will start on port 8080 and you can access api using following base path on local


```bash
  http://localhost:8080/api/v1/station
```