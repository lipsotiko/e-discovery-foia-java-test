# eDiscovery FOIA Java Test

A Spring Boot web application that analyzes quotes using the Apache Open NLP library

## Prerequisites
- Java 14
- Maven
- NPM

## Local Development

### Backend
```shell script
mvn clean
mvn spring-boot:run
```
Backend will be available here: http://localhost:8080

### Frontend
```shell script
cd client
npm install
npm run serve
```
Frontend will be available here: http://localhost:8081

### Tests
```shell script
mvn test
```

## Build
```shell script
./build.sh
```
After a build the application can be viewed here: http://localhost:8080/