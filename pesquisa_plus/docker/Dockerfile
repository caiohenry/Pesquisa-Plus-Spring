# Use uma imagem base do OpenJDK para Java 17
FROM openjdk:17-jre-slim

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o arquivo JAR da aplicação para o contêiner
COPY target/pesquisa_plus.jar /app/pesquisa_plus.jar

# Comando a ser executado quando o contêiner for iniciado
CMD ["java", "-jar", "pesquisa_plus.jar"]
