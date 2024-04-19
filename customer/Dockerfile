# Stage 1: Build the application with Maven
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . /app
RUN mvn clean package

# Stage 2: Build the Docker image
FROM openjdk:17-jdk-slim AS final
WORKDIR /app
COPY --from=build /app/target/customer-0.0.1-SNAPSHOT.jar /app/customer.jar
EXPOSE 8080
CMD ["java", "-jar", "customer.jar"]

