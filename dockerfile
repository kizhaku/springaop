FROM openjdk:21-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the JAR
COPY target/SpringAop-0.1.jar app.jar

RUN mkdir /logs
VOLUME /logs

EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]

