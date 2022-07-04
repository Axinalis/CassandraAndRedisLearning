FROM openjdk:17-alpine

COPY ./target/CassandraAndRedisLearning-0.0.1.jar ./CassandraAndRedisLearning-0.0.1.jar

CMD ["java", "-jar", "CassandraAndRedisLearning-0.0.1.jar"]