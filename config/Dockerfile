FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER caopf@jiedaibao.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /platform-config

WORKDIR /platform-config

EXPOSE 8888

ADD ./platform-config/target/platform-config.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar platform-config.jar
