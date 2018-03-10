FROM openjdk:8-jdk
RUN apt-get update && apt-get install -y sshpass
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]