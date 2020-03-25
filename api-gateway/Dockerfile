FROM openjdk:8-jdk-alpine

LABEL "original.develop.author1" = "Jether Rodrigues <jetherrodrigues@gmail.com>" \
      "original.develop.author2" = "Israel Andrade <israelg.andrade@gmail.com>" \
        "owner" = "CCB - Congregação Cristã no Brasil"

VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} api-gateway.jar

EXPOSE 9999

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/api-gateway.jar"]