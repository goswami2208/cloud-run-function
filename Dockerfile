# Stage 1: Build the function using a Maven container
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
# Copy the pom.xml and download dependencies first for better layer caching
COPY pom.xml .
RUN mvn dependency:go-offline
# Copy the rest of the source code and build the package
COPY src ./src
# The function-maven-plugin creates the executable jar in target/deployment
RUN mvn package

# Stage 2: Create the final, lightweight runtime container
FROM openjdk:21-slim
WORKDIR /app
# Copy the packaged function from the build stage
#COPY --from=build /app/target/deployment .
COPY --from=build /app/target/*.jar my-function-b.jar

# The Functions Framework starts the server on port 8080 by default
EXPOSE 8080
# This command runs your function using the Functions Framework's built-in server
CMD ["java", "-jar", "my-function-b.jar"]