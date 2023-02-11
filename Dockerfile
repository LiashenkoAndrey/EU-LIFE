FROM openjdk:17-alpine

COPY target/eu-life-0.0.1-SNAPSHOT.jar eu-life.jar

ENTRYPOINT ["java", "-jar", "eu-life.jar"]
#ENTRYPOINT ["mvn", "spring-boot:run"]