FROM openjdk:17-jdk-alpine
COPY build/libs/game_controllers-0.0.1-SNAPSHOT.jar /game_controllers-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/game_controllers-0.0.1-SNAPSHOT.jar"]
