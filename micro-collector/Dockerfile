FROM openjdk:17-jdk-alpine
COPY target/micro-collector-0.0.1-SNAPSHOT.jar micro-collector-server-1.0.0.jar
ENTRYPOINT ["java","-jar","/micro-collector-server-1.0.0.jar"]