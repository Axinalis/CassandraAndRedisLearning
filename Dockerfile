FROM openjdk:17-alpine

COPY ./target/ ./

CMD ["java", "-jar", "CassandraAndRedisLearning-0.0.1.jar"]