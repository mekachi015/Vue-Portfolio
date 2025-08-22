# Build stage - use available Maven image
FROM maven:3.8-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/Portfolio-Backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081

# Set environment variables for MongoDB
ENV SPRING_DATA_MONGODB_URI=mongodb://mongodb:27017/Portfolio_Backend
ENV SPRING_DATA_MONGODB_DATABASE=Portfolio_Database

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]