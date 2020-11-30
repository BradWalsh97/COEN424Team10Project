docker build -t sizzle/spring-boot-backend ./Backend/sizzle/ #Build the Backend
docker build -t sizzle/quasar_fe ./Frontend/sizzle/   #Build the Frontend
docker build -t sizzle/nginx_reverse_proxy ./Nginx/  #Build the reverse proxy

docker tag sizzle/spring-boot-backend:latest 609529967983.dkr.ecr.us-east-2.amazonaws.com/sizzle/spring-boot-backend:latest
docker tag sizzle/quasar_fe:latest 609529967983.dkr.ecr.us-east-2.amazonaws.com/sizzle/quasar_fe:latest
docker tag sizzle/nginx_reverse_proxy:latest 609529967983.dkr.ecr.us-east-2.amazonaws.com/sizzle/nginx_reverse_proxy:latest
docker tag mongo 609529967983.dkr.ecr.us-east-2.amazonaws.com/sizzle/mongodb:latest

docker push 609529967983.dkr.ecr.us-east-2.amazonaws.com/sizzle/spring-boot-backend:latest
docker push 609529967983.dkr.ecr.us-east-2.amazonaws.com/sizzle/quasar_fe:latest
docker push 609529967983.dkr.ecr.us-east-2.amazonaws.com/sizzle/nginx_reverse_proxy:latest
docker push 609529967983.dkr.ecr.us-east-2.amazonaws.com/sizzle/mongodb:latest