FROM maven:latest as BUILDER

MAINTAINER Ernest Sado

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package

FROM openjdk:latest
WORKDIR /app
COPY --from=BUILDER /build/target/app.jar /app/
ENTRYPOINT ["java", "-jar", "app.jar"]