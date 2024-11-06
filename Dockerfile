FROM amazoncorretto:17

WORKDIR /app

COPY target/starwars_api-0.0.1-SNAPSHOT.jar /app/starwars_api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "starwars_api.jar"]