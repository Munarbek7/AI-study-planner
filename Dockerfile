# Этап 1: Сборка артефакта через Maven с Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Этап 2: Минимальный образ JRE 21 для запуска в продакшене
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Указываем дефолтный порт, который Render сможет переопределить
ENV PORT=8080
EXPOSE 8080

# Жесткая привязка встроенного Tomcat к порту из переменной окружения Render
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT} -jar app.jar"]