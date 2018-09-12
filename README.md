# Dummy Web app
[![Codeship Status for naXa777/dummy-web-app](https://app.codeship.com/projects/303171c0-98fc-0136-b15a-3601fc6081f4/status?branch=master)](https://app.codeship.com/projects/305391)

A primitive web application developed in Java 7.

## Dependencies

* Spring MVC
* Spring Core
* Hibernate 4.3 / JPA 2.1
* Lombok

**RDBMS**: MySQL;  
**Build system**: Gradle 4;  
**Servlet container**: Tomcat 6+;  
**View technologies**: JSP / JSTL;  
**Logging utility**: Slf4j.

## How to build & run?

* Make sure that MySQL service is up & running.
* Configure connection properties in /resources/db.properties and /resources/hibernate.cfg.xml files.
* Create DB schema manually executing /resources/import.sql script.
* Install Gradle if you don't have it yet.

### Using command line

1. Package app  
    In the root project directory run

       gradle war

2. Deploy university.war to Tomcat  
   Just copy the war file (located in build\libs) to webapps folder and launch Tomcat via bin\catalina.bat or bin/catalina.sh

       bin\catalina.bat start

       bin/catalina.sh start

3. Navigate to http://localhost:8080/university/list in your favourite browser

### Using IntelliJ IDEA

Make sure that you have Lombok support plugin installed & enabled in your IDE.

1. Import project in IDEA
2. Create new Run/Debug configuration (Tomcat Server > Local) and configure your application server (specify path to Tomcat in your environment).
3. Run this configuration

## Screenshots

![Home page](/screenshots/homepage.png)

![Students list](/screenshots/students.png)
