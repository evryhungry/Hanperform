FROM openjdk:17-jdk-slim

WORKDIR /app

COPY jar/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/app/app.jar"]