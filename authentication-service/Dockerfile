FROM openjdk:8-jdk-alpine
RUN apk update && apk upgrade && apk add netcat-openbsd

LABEL Author="Chandrashekhar Prakash Otari"

ENV APP_PATH=/usr/local/

WORKDIR ${APP_PATH}
COPY target/*.jar app.jar
COPY entrypoint.sh entrypoint.sh
RUN chmod +x entrypoint.sh
CMD ./entrypoint.sh ${APP_PATH}