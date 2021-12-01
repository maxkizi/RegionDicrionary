FROM adoptopenjdk/openjdk11:alpine-jre
COPY /target/RegionDicrionary-1.0-SNAPSHOT.jar RegionDicrionary.jar
ENTRYPOINT ["java", "-jar", "RegionDicrionary.jar"]