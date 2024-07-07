FROM maven:3.8.5-openjdk-17-slim AS builder

WORKDIR /app

COPY pom.xml ./
COPY src /app/src

RUN mvn clean package

FROM openjdk:17-slim

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

CMD ["$@"]
