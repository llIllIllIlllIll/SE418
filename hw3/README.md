# README
## 使用方法
- A. 在bash中依次输入下列命令：
```bash
docker network create mynet
docker run --network mynet --name wlhost -p 9000:9000 -p 9001:9001 softpudding/se418hw3:wordladder
docker run --network mynet --name authhost -p 9002:9002 softpudding/se418hw3:auth
```
可以看到这里用了两个image。其中auth用于登陆授权，wordladder是主程序。
- B. 打开浏览器进行测试：
1. 输入 http://localhost:9000/wl/search?w1=dog&w2=cat
由于未登陆，会出现提示：You Have Not Logged In.
2. 输入 http://localhost:9000/wl/login?name=softpudding&pwd=123456
页面会返回true，表示登陆成功。
3. 在此输入 http://localhost:9000/wl/search?w1=dog&w2=cat 会得到正确结果。

## 两个Dockerfile
- Auth模块的Dockerfile
```Dockerfile
#文件目录中有：Dockerfile Auth.jar
FROM openjdk
WORKDIR /app
ADD . /app
EXPOSE 9002
CMD ["java","-jar","Auth.jar"]
```
- Wordladder模块的Dockerfile
```Dockerfile
#文件目录中有：Dockerfile Wl.jar Dictionary.txt
FROM openjdk
WORKDIR /app
ADD . /app
EXPOSE 9000
EXPOSE 9001
CMD ["java","-jar","Wl.jar"]
```
## 一点说明
image弄的有点太大了，应该是用的jdk版本的问题。很抱歉。
