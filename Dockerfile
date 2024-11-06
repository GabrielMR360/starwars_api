FROM amazoncorretto:17

WORKDIR /app

COPY target/starwars_api-0.0.1-SNAPSHOT.jar /app/starwars_api.jar

COPY wait-for-it.sh /app/wait-for-it.sh

RUN chmod +x /app/wait-for-it.sh

EXPOSE 8080

ENTRYPOINT ["./wait-for-it.sh", "database:3306", "--", "java", "-jar", "starwars_api.jar"]