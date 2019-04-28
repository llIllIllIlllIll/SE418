# TASK III
## 关于我的Dockerfile
由于我用的是一个编译好的jar文件，我的环境里只需要一个openjdk就可以了。所以我的Dockerfile还是很简单的：
```Dockerfile
#根目录下有三个文件 Dockerfile hw2-0.1.0.jar dictionary.txt
FROM openjdk
WORKDIR /app
ADD . /app
EXPOSE 9000 #9000 是应用层端口
EXPOSE 9001 #9001 是actuator端口
CMD ["java","-jar","hw2-0.1.0.jar"]
```
## 关于我的镜像
运行我的镜像，请在您的命令行里输入：
```bash
docker run -p 9000:9000 -p 9001:9001 softpudding/se418task3:task3
```
如果不能运行，请检查您的9000端口和9001端口是否都有开放。 
