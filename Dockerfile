FROM openjdk:13-oracle
COPY ./build/libs/*.jar /usr/src/application.jar
ENTRYPOINT ["java", "-jar", "/usr/src/application.jar"]
