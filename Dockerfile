FROM eclipse-temurin:17-jre
WORKDIR src
COPY target*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]