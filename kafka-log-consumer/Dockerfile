FROM amazoncorretto:11

WORKDIR $WORK_DIR
ADD target/kafka-log-consumer-1.0.0-jar-with-dependencies.jar consumer.jar
ADD src/main/resources/kafka.properties kafka.properties
ADD src/main/resources/log4j.properties log4j.properties

# Volumes
VOLUME ["/tmp/docker/input", "/tmp/docker/output", "/tmp/docker/data/logs"]

CMD ["java", "-jar", "consumer.jar", "kafka.properties"]