# ERP BACK

Projeto para controle de finanças pessoais com Java/Spring Boot

Configurações gerais:

app.auth.jwtSecret=secretKey;
server.port=21200
spring.security.oauth2.client.registration.github.client-id=client-id  
spring.security.oauth2.client.registration.github.client-secret=client-secret  
spring.security.oauth2.client.registration.google.client-id=client-id  
spring.security.oauth2.client.registration.google.client-secret=client-secret  

Configurações para utilizar MySQL:

SPRING_DATASOURCE_DRIVERCLASSNAME=com.mysql.cj.jdbc.Driver;  
SPRING_DATASOURCE_URL=jdbc:mysql://databaseAddress:port/databaseName  
SPRING_DATASOURCE_USERNAME=user;  
SPRING_DATASOURCE_PASSWORD=password;  
SPRING_JPA_HIBERNATE_DDLAUTO=update;  
SPRING_FLYWAY_ENABLED=true;  
