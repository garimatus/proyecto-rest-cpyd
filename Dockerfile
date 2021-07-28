FROM openjdk:11-jre-slim-
EXPOSE 8080
ARG WAR_FILE=target/ProyectoRestZ.war
ADD ${WAR_FILE} ProyectoRestZ.war
ENTRYPOINT ["java","-jar","/ProyectoRestZ.war"]