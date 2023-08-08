# 基础镜像
FROM java:8

# 把eureka-server的jar包拷贝到将来启动的容器中
COPY target/eureka-server-0.0.1-SNAPSHOT.jar /home

# 启动eureka-server
CMD java -jar /home/eureka-server-0.0.1-SNAPSHOT.jar