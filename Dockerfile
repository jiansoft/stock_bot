# 使用官方 Gradle 鏡像作為建構環境
FROM gradle:jdk21 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

# 使用官方 OpenJDK 鏡像運行建構好的 JAR 檔案
FROM openjdk:21-slim
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar

RUN ln -snf /usr/share/zoneinfo/Asia/Taipei /etc/localtime && echo Asia/Taipei > /etc/timezone

WORKDIR /app
ADD .env .

ENTRYPOINT ["java","-jar","/app/spring-boot-application.jar"]