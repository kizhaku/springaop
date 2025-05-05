FROM openjdk:21-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the JAR
COPY target/SpringAop-0.0.1-SNAPSHOT.jar app.jar

RUN mkdir /logs
VOLUME /logs

EXPOSE 80

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]

