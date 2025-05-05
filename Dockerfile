FROM openjdk:17-jdk-slim
#ARG JAR_FILE=build/libs/*.jar
#COPY ${JAR_FILE} app.jar
COPY CodiOn-BE-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=prod"]
