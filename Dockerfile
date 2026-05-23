# Этап 1: Сборка
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Этап 2: Запуск
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Явно говорим Docker открыть порт 8080 для внешнего мира
EXPOSE 8080

# Передаем порт серверу Spring Boot через аргументы запуска
ENTRYPOINT ["java", "-Dserver.port=8080", "-jar", "app.jar"]