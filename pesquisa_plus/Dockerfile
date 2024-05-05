# Use uma imagem base do OpenJDK para Java 17
FROM openjdk:17-jdk-slim

# Instale o Maven
RUN apt-get update && \
    apt-get install -y maven

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o arquivo pom.xml para o diretório de trabalho
COPY pom.xml .

# Copie todo o código-fonte para o diretório de trabalho
COPY src ./src

EXPOSE 8080
