FROM maven:3.9.8-eclipse-temurin-21-alpine AS build
COPY pom.xml /build/
WORKDIR /build/
RUN mvn dependency:go-offline
COPY src /build/src/
RUN mvn clean install -DskipTests

#Run stage
FROM openjdk:21
ARG JAR_FILE=/build/target/*.jar
COPY --from=build $JAR_FILE /opt/decision-service/service.jar
ENTRYPOINT [ "java", "-jar", "/opt/decision-service/service.jar" ]