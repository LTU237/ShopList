FROM openjdk:21
EXPOSE 8080
ADD target/shopk8.jar /opt/shopk8.jar
CMD ["java", "−jar", "/shopk8.jar"]
