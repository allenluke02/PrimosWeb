#FRom store/oracle/serverjre:8
FROM adoptopenjdk/openjdk11:alpine-jre

RUN apk add --update \
    curl \
    && rm -rf /var/cache/apk/*

RUN apk add --update \
    tzdata \
    && rm -rf /var/cache/apk/*

#Add volume for logs
VOLUME	/home/admin/logs/fo

ENTRYPOINT ["java","-jar","/usr/share/bi/bi-foapi.jar"]
ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/bi/bi-foapi.jar
