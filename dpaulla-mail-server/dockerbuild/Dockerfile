FROM anapsix/alpine-java
VOLUME /tmp
EXPOSE 8181
ADD conf /tmp/conf
ADD mercury-mail-server-4.0.1-SNAPSHOT.jar /tmp/mercury-mail-server.jar
RUN sh -c 'touch /tmp/mercury-mail-server.jar'
CMD java -Djava.security.egd=file:/dev/./urandom -jar /tmp/mercury-mail-server.jar --spring.config.location=file:/tmp/conf/