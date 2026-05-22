# Etapa 1: Build con Maven y Java 17
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución
FROM openjdk:17.0.1-jdk-slim
# Tu pom.xml dice que el archivo generado se llama backend-0.0.1-SNAPSHOT.jar
COPY --from=build /target/backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]