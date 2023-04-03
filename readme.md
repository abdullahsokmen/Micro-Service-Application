# RabbitMQ docker
     
    docker run -d --hostname my-rabbit --name some-rabbit -p 15672:15672 -p 5672:5672 
    -e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=root rabbitmq:3-management

## Docker image oluşturmak

        docker build --build-arg JAR_FILE=ConfigServerGit/build/libs/ConfigServerGit-v.0.1.jar -t abdullahsk11/java6configservergit:v.0.1 .

    docker build --build-arg JAR_FILE=AuthMicroService/build/libs/AuthMicroService-v.0.1.jar -t abdullahsk11/java6auth:v.0.1 .

    docker build --build-arg JAR_FILE=UserProfileMicroService/build/libs/UserProfileMicroService-v.0.1.jar -t abdullahsk11/java6user:v.0.1 .