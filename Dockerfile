# --------------------------------------
# Stage 1: Build the application using Maven
# --------------------------------------
FROM maven:3.9.9-eclipse-temurin-17 AS build

# Set work directory
WORKDIR /app

# Copy pom.xml and download dependencies (leverage cache)
COPY pom.xml .

# 🛠️ Download dependencies with retries for stability
RUN mvn -B -Dmaven.wagon.http.retryHandler.count=5 \
         -Dmaven.wagon.httpconnectionManager.ttlSeconds=120 \
         dependency:go-offline

# Now copy the actual source code
COPY src ./src

# 🧪 Build the JAR (skip tests for CI build speed)
RUN mvn package -DskipTests

# --------------------------------------
# Stage 2: Create the runtime image using JRE only
# --------------------------------------
FROM eclipse-temurin:17-jre-jammy

# Set working directory in container
WORKDIR /app

# Copy the packaged JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
