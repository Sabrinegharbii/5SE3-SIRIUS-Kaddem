FROM openjdk:11-alpine

ADD target/khaddem-*.jar /khaddem.jar

CMD ["java", "-jar", "/khaddem.jar"]
