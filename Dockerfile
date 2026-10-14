
FROM maven:3.9-eclipse-temurin-26 AS build
WORKDIR /app

COPY backend/ .
RUN chmod +x mvnw && ./mvnw -DskipTests package

# Run backend
FROM eclipse-temurin:26-jre
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 10000
CMD ["sh", "-c", "java -Dserver.port=${PORT:-10000} -jar app.jar"]
