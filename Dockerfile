FROM openjdk:19-jdk-alpine
COPY target/TwitterLikeApplication-0.0.1-SNAPSHOT.jar /user/app/app.jar
WORKDIR /user/app
ENTRYPOINT ["java","-jar","/user/app/app.jar"]
