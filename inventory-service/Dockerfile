FROM openjdk:8-jdk-alpine
RUN apk update && apk upgrade && apk add netcat-openbsd

LABEL Author="Chandrashekhar Prakash Otari"

ENV APP_PATH=/usr/local/inventory-service
ENV DISCOVERY_SERVER_URL=http://localhost:8761/eureka
ENV KAFKA_BROKER=localhost
ENV OAUTH2_RESOURCE_USER_URI=http://localhost:10001/authentication-service/user

RUN mkdir -p ${APP_PATH}
WORKDIR ${APP_PATH}
COPY target/*.jar app.jar
COPY entrypoint.sh entrypoint.sh
RUN chmod +x entrypoint.sh
CMD ./entrypoint.sh ${APP_PATH} ${DISCOVERY_SERVER_URL} ${KAFKA_BROKER} ${OAUTH2_RESOURCE_USER_URI}