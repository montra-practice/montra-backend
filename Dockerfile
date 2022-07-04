FROM openjdk:8-jdk-alpine
ARG JAR_FILE
ADD target/${JAR_FILE} montra.jar
EXPOSE 9091
ENTRYPOINT ["java", "-jar", "/montra.jar"]