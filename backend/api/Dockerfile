FROM maven:3.9.11-amazoncorretto-21-alpine AS build-stage
WORKDIR /home/app
COPY . .
RUN ./mvnw -Dmaven.test.skip=true clean package

FROM amazoncorretto:21-alpine AS production-stage
WORKDIR /home/app
EXPOSE 8080
COPY --from=build-stage /home/app/target/*.jar api.jar
ENTRYPOINT ["java", "-jar", "api.jar"]