#FROM openjdk:19-jdk
#
#WORKDIR /app
#
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#
#RUN ./mvnw dependency:go-offline
#COPY src ./src
#EXPOSE 8080
#
#CMD["",""]

FROM adoptopenjdk/openjdk11:jdk-11.0.2.9-slim
WORKDIR /SpringBootJwtdemo
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

EXPOSE 8080
COPY src ./src
CMD ["./mvnw","spring-boot:run"]
