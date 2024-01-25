FROM openjdk:21
RUN mkdir −p /opt/shoplistk8
WORKDIR /opt/shoplistk8
COPY target/shoplistk8.jar /opt/shoplistk8
CMD ["java", "−jar", "shoplistk8.jar"]
EXPOSE 8080