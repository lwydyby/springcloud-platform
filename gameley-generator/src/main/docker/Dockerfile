FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD gameley-generator-1.0.0-SNAPSHOT.jar app.jar
ADD wait-for-it.sh /wait-for-it.sh
RUN sh -c 'touch /app.jar'
RUN sh -c 'chmod 777 /wait-for-it.sh'
