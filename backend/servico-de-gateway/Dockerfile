# Step 1: build
FROM maven:3.9.6-eclipse-temurin-8 AS build
WORKDIR /app

# Copia todo o código para o container
COPY . .

# Compila o projeto, pulando os testes
RUN mvn clean package -DskipTests

# Step 2: runtime
FROM eclipse-temurin:8-jdk
WORKDIR /servico_de_autenticacao

# Copia o JAR compilado da fase de build
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta que o serviço vai rodar
EXPOSE 8000

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
