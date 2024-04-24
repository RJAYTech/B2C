# Stage 1: Build the application with Maven
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . /app
RUN mvn clean package

# Stage 2: Build the Docker image
FROM openjdk:17-jdk-slim AS final
WORKDIR /app
COPY --from=build /app/target/admin-0.0.1-SNAPSHOT.jar /app/admin.jar
EXPOSE 8081
CMD ["java", "-jar", "admin.jar"]

