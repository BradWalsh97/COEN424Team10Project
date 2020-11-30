docker build -t sizzle/spring-boot-backend ./Backend/sizzle/ #Build the Backend
docker build -t sizzle/quasar_fe ./Frontend/sizzle/   #Build the Frontend
docker build -t sizzle/nginx_reverse_proxy ./Nginx/  #Build the reverse proxy
#cd Backend/sizzle
#mvn spring-boot:build-image -Dspring-boot.build-image.imageName=sizzle/spring-boot_backend

docker-compose up #run docker compose starting the services
