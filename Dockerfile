FROM openjdk:17
COPY build/libs/*.jar backend.jar
ENTRYPOINT java -jar backend.jar
EXPOSE 8080