FROM amazoncorretto:21-alpine

LABEL mentainer="dev.erfaniii@gmail.com"

WORKDIR /app

COPY target/todo-0.0.1-SNAPSHOT.jar /app/todo.jar

ENTRYPOINT ["java", "-jar", "todo.jar"]