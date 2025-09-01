# Multi-stage build for Spring Boot backend (bundles Angular via Maven profile)
# Build context should be the repository root.

FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /workspace

# Copy only the relevant project folders to leverage Docker cache effectively
COPY revature/JWAPrimer/Junit1/productApp /workspace/revature/JWAPrimer/Junit1/productApp
COPY revature/JWAPrimer/Junit1/Product-Jwa_us /workspace/revature/JWAPrimer/Junit1/Product-Jwa_us

WORKDIR /workspace/revature/JWAPrimer/Junit1/productApp
# Build with the profile that builds the Angular app and bundles it
RUN ./mvnw -Pwith-frontend -DskipTests clean package

FROM eclipse-temurin:17-jre
ENV JAVA_OPTS=""
WORKDIR /app

# Copy the built jar
COPY --from=build /workspace/revature/JWAPrimer/Junit1/productApp/target/*.jar /app/app.jar

# Expose backend port
EXPOSE 9090

# Environment overrides (optional)
# ENV SPRING_PROFILES_ACTIVE=prod
# ENV APP_CORS_ALLOWED_ORIGINS=

RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*
HEALTHCHECK --interval=30s --timeout=5s --start-period=30s --retries=5 CMD curl -fsS http://localhost:9090/app/actuator/health || exit 1

ENTRYPOINT ["/bin/sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
