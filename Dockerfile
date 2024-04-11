# 使用 adoptopenjdk 17 作为基础镜像
FROM maven:3.5-jdk-8-alpine as builder

# Copy local code to the container image.
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build a release artifact.
RUN mvn package -DskipTests

# Run the web service on container startup. 
CMD ["java","-jar","/app/target/user-center-backend-1.0-SNAPSHOT.jar","--spring.profiles.active=prod"]