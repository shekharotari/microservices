#!/bin/sh

# while ! 'nc -z discovery-server 8761'; do
#	echo "Waiting for discovery-server";
#	sleep 3;
# done

# echo "discovery-server is started";

#java -jar ${1}/app.jar --eureka.client.serviceUrl.defaultZone=${2}

echo ${1}
echo ${2}

java -jar ${1}/app.jar --eureka.client.serviceUrl.defaultZone=${2}