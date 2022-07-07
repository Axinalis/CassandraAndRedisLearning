FROM openjdk:17-alpine

COPY ./target/ ./

COPY ./lib/CassandraJDBC42.jar ./libs/CassandraJDBC42.jar

CMD ["java", "-jar", "CassandraAndRedisLearning-0.0.1.jar"]