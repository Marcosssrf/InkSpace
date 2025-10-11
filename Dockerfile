# Estágio 1: Build com Maven
# Usamos uma imagem oficial do Maven com Java 21 para compilar o projeto.
FROM maven:3.9-eclipse-temurin-21 AS build

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o pom.xml primeiro para aproveitar o cache de dependências do Docker
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia todo o resto do código-fonte
COPY src ./src

# Roda o comando para compilar o projeto e gerar o .jar, pulando os testes
RUN mvn clean package -DskipTests


# Estágio 2: Execução
# Usamos uma imagem muito menor, apenas com o Java necessário para rodar (JRE).
FROM eclipse-temurin:21-jre-jammy

# Define o diretório de trabalho
WORKDIR /app

# Copia apenas o arquivo .jar gerado no estágio anterior para o nosso contêiner final
COPY --from=build /app/target/app-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta 8080, onde a aplicação Spring Boot roda
EXPOSE 8080

# Comando para iniciar a aplicação quando o contêiner for executado
ENTRYPOINT ["java", "-jar", "app.jar"]