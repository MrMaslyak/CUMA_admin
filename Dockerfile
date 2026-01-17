FROM openjdk:21-ea-slim
LABEL authors="maslyak"
WORKDIR /app

COPY target/CUMA_admin-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]