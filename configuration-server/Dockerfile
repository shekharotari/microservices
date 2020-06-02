FROM openjdk:8-jdk-alpine
RUN apk update && apk upgrade && apk add netcat-openbsd

LABEL Author="Chandrashekhar Prakash Otari"

ENV APP_PATH=/usr/local/configuration-server
ENV DISCOVERY_SERVER_URL=http://localhost:8761/eureka
ENV PROFILE_ACTIVE=XXX
ENV GIT_USER=YYY
ENV GIT_PASSWORD=ZZZ

RUN mkdir -p ${APP_PATH}
WORKDIR ${APP_PATH}
COPY target/*.jar app.jar
COPY entrypoint.sh entrypoint.sh
RUN chmod +x entrypoint.sh
CMD ./entrypoint.sh ${APP_PATH} ${PROFILE_ACTIVE} ${GIT_USER} ${GIT_PASSWORD} ${DISCOVERY_SERVER_URL}