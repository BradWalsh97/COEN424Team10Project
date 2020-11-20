#!bin/bash

docker build -t sizzle/spring-boot-backend ./Backend/ #Build the Backend
docker build -t sizzle/quasar_fe ./Frontend/sizzle/   #Build the Frontend
docker build -t sizzle/nginz_reverse_proxy/ ./nginx/  #Build the reverse proxy

docker-compose up #run docker compose starting the services