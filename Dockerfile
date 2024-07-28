FROM amazoncorretto:17
ARG JAR_FILE=target/*.jar
WORKDIR /petclinic
COPY ${JAR_FILE} petclinic.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "petclinic.jar" ]
