FROM openjdk:17-jdk-alpine
ARG JAR_FILE
COPY ${JAR_FILE} mowitnow.jar
ENTRYPOINT ["java","-jar","/mowitnow.jar"]