FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} mangojelly_back.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/mangojelly_back.jar"]