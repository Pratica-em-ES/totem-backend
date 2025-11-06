# Use OpenJDK 21 as base image
FROM eclipse-temurin:21 as build

# Set working directory
WORKDIR /app

# Copy Maven files
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Download dependencies (cache layer)
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src src

# Build the application
RUN ./mvnw clean package -DskipTests

# Production stage
FROM eclipse-temurin:21

# Create app user
RUN groupadd -r appuser && useradd -r -g appuser appuser

# Set working directory
WORKDIR /app

# Copy JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Change ownership to app user
RUN chown -R appuser:appuser /app
USER appuser

# Expose port
EXPOSE 8080

# Run the application with optimized JVM settings for Cloud Run
ENTRYPOINT ["java", "-Xmx512m", "-Xms256m", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar"]