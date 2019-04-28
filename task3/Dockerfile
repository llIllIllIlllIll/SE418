FROM openjdk
WORKDIR /app
ADD . /app
EXPOSE 9000 #9000 是应用层端口
EXPOSE 9001 #9001 是actuator端口
CMD ["java","-jar","hw2-0.1.0.jar"]