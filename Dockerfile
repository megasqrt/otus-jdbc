FROM maven:3.5.4-jdk-11

COPY . /app/

WORKDIR /app

CMD ["mvn", "clean", "spring-boot:run", "-Drun.profiles=doc"]

EXPOSE 8080