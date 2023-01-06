FROM openjdk:11
EXPOSE 8080
ADD target/beWatching-docker.jar beWatching-docker.jar
ENTRYPOINT ["java","-jar","beWatching-docker.jar"]
