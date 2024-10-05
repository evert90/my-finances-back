FROM gradle:7.5.1-jdk17-alpine AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy Gradle configuration and source files
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle
COPY src ./src

# Build the application using the Gradle wrapper (if present) or Gradle directly
RUN ./gradlew clean assemble --no-daemon

# Stage 2: Create a minimal image with Amazon Corretto JDK 17 to run the application
FROM amazoncorretto:17.0.12-al2023-headless

# Set the working directory inside the container
WORKDIR /app

# Copy the built jar file from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the application's default port (change if necessary)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
