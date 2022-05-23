FROM openjdk:11
EXPOSE 8080
ADD target/electricity-provider-docker.jar electricity-provider-docker.jar
ENTRYPOINT ["java", "-jar", "electricity-provider-docker.jar"]