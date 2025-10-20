# Backend do Projeto TecnoGuia

Escolha uma das opções abaixo para rodar o projeto:

## 🐳 Opção 1 - Usando Docker (recomendado)

[https://www.docker.com/products/docker-desktop/](https://www.docker.com/products/docker-desktop/)

## ☕ Opção 2 - Modo Dev local (sem Docker)

[https://www.oracle.com/br/java/technologies/downloads/](https://www.oracle.com/br/java/technologies/downloads/)

# ▶️ Executando com Docker (Opção 1)

1. Criar um arquivo .env na raiz do projeto

```bash
SPRING_PROFILES_ACTIVE=prod
DATABASE_URL=jdbc:postgresql://<host>:5432/postgres?user=<usuario>&password=<senha>
```

Para acessar o conteúdo que você deve colcoar nesse .env, clique aqui: COLOCAR LINK

2. Iniciar o Docker Desktop

Abra o aplicativo Docker Desktop. Aguarde até o status no canto inferior esquerdo mudar de
Engine Starting... para Engine Running.

3. Rodar o projeto

No terminal, dentro da pasta totem-backend, execute:

```bash
docker-compose up --build
```

Esse comando irá construir as imagens Docker e iniciar os serviços definidos no `docker-compose.yml`.
Na primeira execução, isso pode demorar um pouco devido ao download de dependências.

4. Acessar a aplicação

Abra o navegador e vá para: [http://localhost:8080](http://localhost:8080)

# ▶️ Executando localmente (Opção 2 - modo dev)

1. Rodar com Maven Wrapper. No terminal, dentro da pasta totem-backend, execute:

```bash
./mvnw spring-boot:run
```

Isso iniciará o projeto em modo dev, utilizando o banco de dados H2 em memória por padrão.

2. Acessar a aplicação

Abra o navegador e vá para: [http://localhost:8080](http://localhost:8080)
