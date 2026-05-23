# Этап 1: Сборка (Используем Maven с Java 21)
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Этап 2: Запуск (Используем среду выполнения JRE 21)
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Настройка портов для Render под Java 21
ENV PORT=8080
EXPOSE 8080

# Запуск приложения
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT} -jar app.jar"]