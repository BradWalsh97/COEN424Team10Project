#TODO: need to make sure that java 8 is the right one
#consider using jre
#or make multi-stage 1. get all dependencies and then make jar. cpy jar into last stage
#FROM openjdk:8-jdk-alpine
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE}} app.jar
#ENTRYPOINT [ "java", "-jar", "/app.jar"]

#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
WORKDIR /app
COPY src src
COPY pom.xml .
RUN ls
RUN mvn -f pom.xml clean package

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /app/build/sizzle-0.0.1.jar /usr/local/lib/app.jar
EXPOSE 8181
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]