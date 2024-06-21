FROM openjdk:17-jdk-alpine
COPY target/micro-sender-0.0.1-SNAPSHOT.jar micro-sender-server-1.0.0.jar
ENTRYPOINT ["java","-jar","/micro-sender-server-1.0.0.jar"]