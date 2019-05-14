FROM openjdk:8
ENV DOCKER_CONTAINER Yes
ADD target/user-authentication.jar user-authentication.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "user-authentication.jar"]