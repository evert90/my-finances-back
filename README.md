# ERP BACK

Projetinho com Java/Spring Boot

Config to run using MYSQL:

SPRING_DATASOURCE_DRIVERCLASSNAME=com.mysql.cj.jdbc.Driver;  
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/databaseName?autoReconnect=true&allowPublicKeyRetrieval=true&useSSL=false;  
SPRING_DATASOURCE_USERNAME=user;  
SPRING_DATASOURCE_PASSWORD=password;  
SPRING_JPA_HIBERNATE_DDLAUTO=update;  
SPRING_FLYWAY_ENABLED=true;  
APP_AUTH_JWTSECRET=secretKey;  
SERVER_PORT=21200;  
SERVER_ADDRESS=127.0.0.1;

Config to run using PostgreSQL:

SPRING_DATASOURCE_DRIVERCLASSNAME=org.postgresql.Driver;  
SPRING_DATASOURCE_URL=jdbc:postgresql://databaseAddress:port/databaseName  
SPRING_DATASOURCE_USERNAME=user;  
SPRING_DATASOURCE_PASSWORD=password;  
SPRING_JPA_HIBERNATE_DDLAUTO=update;  
SPRING_FLYWAY_ENABLED=false;  
APP_AUTH_JWTSECRET=secretKey;  
SERVER_PORT=21200;  
SERVER_ADDRESS=127.0.0.1;  