FROM maven:latest
WORKDIR /usr/src/raccoon_consultant
COPY pom.xml .
RUN mvn -B -f pom.xml -s /usr/share/maven/ref/settings-docker.xml dependency:go-offline -B
COPY . .
RUN mvn -B -s /usr/share/maven/ref/settings-docker.xml package -DskipTests -X

FROM openjdk:14-jdk-alpine
WORKDIR /static
WORKDIR /app
COPY --from=0 /usr/src/raccoon_consultant/target/raccoon_consultant-0.0.1-SNAPSHOT.jar .
EXPOSE 8080


