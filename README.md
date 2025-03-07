# How to setup this project locally

## Prerequisite software.
- PostgreSQL
- Java IDE of choice (IntelliJ is preferred)
- Postman (to test the API)
## Setup steps
- Check out this repo into your local machine.
- create a new database in your local postgreSQL
- navigate to the properties file in your local project, located at `\VirtualClassRoom\src\main\resources\application.properties`
- in the properties file change the `spring.datasource.url` to `jdbc:postgresql://localhost:[port of your postgresql, generally it is 5432]/{Database Name}`
- also change `spring.datasource.username` and `spring.datasource.password` corresponding to your PostgreSQL setup.
- now go ahead and run the project, this should spin up tomcat and the Application will be available at port `8080` of your local host.
- navigate to your browser and go to the following address `http://localhost:8080/`, now should be able to see a HTML page with lorem ipsum, your api is now ready to use.
- open postman and test the end points.
