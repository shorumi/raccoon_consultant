FROM maven:latest
WORKDIR /usr/src/lab-exam
COPY pom.xml .
RUN mvn -B -f pom.xml -s /usr/share/maven/ref/settings-docker.xml dependency:resolve -X
COPY . .
RUN mvn -B -s /usr/share/maven/ref/settings-docker.xml package -DskipTests -X

FROM openjdk:14-jdk-alpine
WORKDIR /static
WORKDIR /app
COPY --from=0 /usr/src/lab-exam/target/lab-exam-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container","-jar", "/app/lab-exam-0.0.1-SNAPSHOT.jar"]
CMD ["--spring.profiles.active=postgres"]

