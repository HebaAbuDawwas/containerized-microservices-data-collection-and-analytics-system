FROM openjdk:17-jdk

WORKDIR /app

COPY target/AuthenticationService-0.0.1-SNAPSHOT.jar authentication-app.jar

EXPOSE 8081

CMD ["java", "-jar", "authentication-app.jar"]
