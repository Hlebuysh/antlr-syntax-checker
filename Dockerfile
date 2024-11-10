FROM ubuntu:latest
LABEL authors="Hlebush"

# Используем официальный образ OpenJDK 17 с поддержкой Maven
FROM maven:3.9.8-eclipse-temurin-17 AS build

# Устанавливаем рабочую директорию в контейнере
WORKDIR /app

# Копируем файл pom.xml и все зависимости для сборки
COPY pom.xml .

# Загружаем зависимости, чтобы они кэшировались отдельно
RUN mvn dependency:go-offline -B

# Копируем весь проект
COPY . .

# Сборка приложения
RUN mvn package -DskipTests

# Создаем новый образ на основе JRE для запуска
FROM eclipse-temurin:17-jre

# Устанавливаем рабочую директорию для приложения
WORKDIR /app

# Копируем сгенерированный JAR файл из предыдущего контейнера
COPY --from=build /app/target/*.jar app.jar

# Указываем команду запуска для Spring Boot приложения
ENTRYPOINT ["java", "-jar", "app.jar"]


ENTRYPOINT ["top", "-b"]