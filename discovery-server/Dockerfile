FROM openjdk:8-jdk-alpine
# RUN addgroup -S spring && adduser -S spring -G spring
# USER spring:spring
VOLUME /tmp
COPY target/*.jar app.jar
ARG CONTAINER_PORT=8761
EXPOSE ${CONTAINER_PORT}
ENTRYPOINT [ "java", "-jar", "/app.jar" ]
