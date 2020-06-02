#!/bin/sh

#while ! 'nc -z discovery-server 8761'; do
#    echo "Waiting for discovery-server";
    sleep 3;
#done

#echo "discovery-server is started";

#java -D -jar ${1}/app.jar --eureka.client.serviceUrl.defaultZone=${2}

java -Dspring.profiles.active=${2} \
     -Dspring.cloud.config.server.git.username=${3} \
     -Dspring.cloud.config.server.git.password=${4} \
     -Deureka.client.serviceUrl.defaultZone=${5} \
     -jar ${1}/app.jar