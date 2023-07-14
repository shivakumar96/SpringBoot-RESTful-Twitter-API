# **Text based Social Media APP RESTful APIs (similar to Twitter)**

## Spring Boot web application Project:
This is Web application project to Manage Todos

## Requirements:
- Java 17 or higher  [here](https://www.oracle.com/java/technologies/downloads/)
- Modules/libraries :
    - Spring Boot 3.1.1
    - Spring Security
    - Hibernate
    - Spring web-starter
    - swagger/openapi
    - H2 Database (in-memory database)

Refer pom.xml for dependencies.
### Note: to execute APIs we need REST client App such as Postman.

## Request Code Access, to download it form the Github
Run the below command to clone code from github
```
git clone https://github.com/shivakumar96/Task-Manger-SpringBoot-webApplication.git
```

## Running the code
Start intellij idea or Eclipse IDE <br />

1. Select 'open an existing Maven project' option and load the project. Make sure the pom.xml has downloaded all necessary Jars. <br>
2. Run the main method in TaskMangerApplication Java class, this will run the server.
3. Open REST client App and execute request, In the authentication headers use credentials (user,user) as username and password

## API Documentation (swagger based)
Run the web browser and type the below URL to access the API Documentation
```
http://localhost:8080/swagger-ui/index.html
```

## API Documentation screenshots:
### List of RESTful APIs 
![Alt text](images/)

### user API
![Alt text](images/)