#!/bin/sh

#while ! 'nc -z discovery-server 8761'; do
#    echo "Waiting for discovery-server";
    sleep 3;
#done

#echo "discovery-server is started";

java -Deureka.client.serviceUrl.defaultZone=${2} \
     -jar ${1}/app.jar