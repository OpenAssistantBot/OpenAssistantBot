FROM openjdk:11-jre
EXPOSE 8080
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} openboot.jar
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=cloud", "/openboot.jar"]
