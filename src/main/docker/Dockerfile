FROM openjdk:8-jdk-alpine
MAINTAINER warthur@163.com

ENV JVM_OPTS=""

RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.ustc.edu.cn/g' /etc/apk/repositories \
        && apk add --update-cache bash \
        && apk add --no-cache ttf-dejavu fontconfig

VOLUME /tmp
ADD app.jar /app.jar
ENTRYPOINT java $JVM_OPTS -Duser.timezone=Asia/ShangHai -Djava.security.egd=file:/dev/./urandom -jar /app.jar