# Dockerfile

FROM eclipse-temurin:21-jdk-alpine

# Create directory
WORKDIR /app

# Copy jar from target
COPY target/ServiceOrder-0.0.1-SNAPSHOT.jar ServiceOrder-0.0.1.jar

# Expose default Spring Boot port
EXPOSE 8080

# Run app
ENTRYPOINT ["java", "-jar", "ServiceOrder-0.0.1.jar"]
