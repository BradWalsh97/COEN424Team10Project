#TODO: need to make sure that java 8 is the right one
FROM openjdk:8-jdk-alpine 
ARG JAR_FILE=targer/*.jar
COPY ${JAR_FILE}} app.jar
ENTRYPOINT [ "java", "-jar", "/app.jar"]