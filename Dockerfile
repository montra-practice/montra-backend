FROM openjdk:8-jdk-alpine
ADD target/Montra-0.0.1-SNAPSHOT.jar montra.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/montra.jar"]