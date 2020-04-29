# eDiscovery FOIA Java Test

A spring boot application that will process some quotes, and provide users with the results.

## Prerequisites
- Java 14
- NPM
- Maven

## Local Development
### Backend

```shell script
  mvn spring-boot:run
```
The Backend will be available at http://localhost:8080

### Frontend
```shell script
    cd client/gdit_test_ui
    npm install
    npm run serve
```
The GUI will be available at http://localhost:8081

## Tests
```shell script
  mvn test
```

## Database

This application is using an in-memory h2 database. To using something other than h2, update the datasource 
parameters in the [applicaiton.yml](/src/main/resources/application.yml) and update the SQL scripts with
the appropriate dialect of SQL, see [DB Migrations](/src/main/resources/db/migration)