# 使用官方的 OpenJDK 17-jdk 作为基础镜像
FROM openjdk:17-jdk

# 将编译好的 JAR 文件复制到容器中的 /app 目录下
COPY user-center-backend-1.0-SNAPSHOT.jar /app/

# 设置容器启动命令
CMD ["java", "-jar", "/app/user-center-backend-1.0-SNAPSHOT.jar", "--spring.profiles.active=prod"]
