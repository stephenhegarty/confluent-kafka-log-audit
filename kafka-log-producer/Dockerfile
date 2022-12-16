FROM amazoncorretto:11

WORKDIR $WORK_DIR
ADD target/kafka-log-producer-1.0.0-jar-with-dependencies.jar producer.jar
ADD src/main/resources/kafka.properties kafka.properties
ADD src/main/resources/log4j.properties log4j.properties

# Volumes
VOLUME ["/tmp/docker/input", "/tmp/docker/output", "/tmp/docker/data/logs"]

CMD ["java", "-jar", "producer.jar", "kafka.properties"]