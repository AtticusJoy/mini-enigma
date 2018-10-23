FROM openjdk:8-jre-alpine

COPY target/rest-0.0.1-SNAPSHOT.jar /home/rest.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","/home/rest.jar"]