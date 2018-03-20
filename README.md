## UserMaintenanceService

### Goal
This project is for learning to create a SpringBoot REST application running in PCF. 

### Description
This service will expose REST services and retrieve its data from a MySQL database.

It exposes REST services provided by the UserMaintenanceService:

- GET:  login(name, password)
- POST: addUser(name, email)
- GET:  findUserBy(name)
- GET:  findUserBy(email)
- GET:  getUser(id)
- POST: updateUser(id,name,email)
- POST: deleteUser(id)

### Techniques used
- SpringBoot
- PCF
- Exposing REST services
- Database MySQL access using Spring
- Redis
- Lombok

### Steps

1. Create a Maven project in IntelliJ.
2. Update the pom.xml to contain SpringBoot dependencies for a REST project.
3. Create a class with SpringBoot annotation to start the app.
4. Deploy to and configure in PCF.
5. Return a basic responses for REST services.
6. Update pom with data access dependencies.
7. Access MySQL database.
8. Add caching with Redis.


#### Step 1
Used Spring Initializr website to create project with:
- JPA
- Lombok
- MySQL
- Dev Tools

Manually added spring-boot-starter-actuator dependency to the project.

Added main class <code>UserManagementServiceApplication</code> test <code>UserManagementServiceApplicationTest</code>

Added .gitignore file.

TODO:
WebTests
E2E Tests
Spring security
Profiles
JWT / login and checking token etc
Deploy to PCF
Config server
Cashing