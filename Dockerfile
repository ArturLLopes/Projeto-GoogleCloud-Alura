# Stage 1: build da aplicação usando Gradle
FROM gradle:8.3-jdk-jammy AS build

# Define o diretório de trabalho
WORKDIR /app

# Copia arquivos de build (build.gradle, settings.gradle, etc) e código fonte
COPY build.gradle.kts settings.gradle.kts ./
COPY src ./src

# Roda o build para gerar o jar
RUN gradle clean bootJar --no-daemon

# Stage 2: imagem final, runtime leve com OpenJDK 21
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copia o jar gerado no build para a imagem final
COPY --from=build /app/build/libs/*.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando para rodar o jar
ENTRYPOINT ["java", "-jar", "app.jar"]
