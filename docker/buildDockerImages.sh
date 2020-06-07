#!/bin/sh

docker rm -f $(docker ps -aq)

docker rmi -f $(cut -d' ' -f3 <<< $(docker image ls -a | grep 'shekharotari' | tr -s ' '))
docker rmi -f $(cut -d' ' -f3 <<< $(docker image ls -a | grep 'none' | tr -s ' '))

cd ~/git/microservices/discovery-server
./mvnw clean install package -Dmaven.test.skip=true
docker build -f Dockerfile -t shekharotari/discovery-server:0.0.1 .

cd ~/git/microservices/configuration-server
./mvnw clean install package -Dmaven.test.skip=true
docker build -f Dockerfile -t shekharotari/configuration-server:0.0.1 .

cd ~/git/microservices/authentication-service
./mvnw clean install package -Dmaven.test.skip=true
docker build -f Dockerfile -t shekharotari/authentication-service:0.0.1 .

cd ~/git/microservices/services-gateway
./mvnw clean install package -Dmaven.test.skip=true
docker build -f Dockerfile -t shekharotari/services-gateway:0.0.1 .

cd ~/git/microservices/inventory-service
./mvnw clean install package -Dmaven.test.skip=true
docker build -f Dockerfile -t shekharotari/inventory-service:0.0.1 .
