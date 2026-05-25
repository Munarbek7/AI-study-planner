# Этап 1: Сборка
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Этап 2: Запуск
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Говорим Render, что приложение умеет работать с портами
EXPOSE 8080

# Инструкция запуска с поддержкой динамических переменных окружения
ENTRYPOINT ["sh", "-c", "java -jar app.jar"]