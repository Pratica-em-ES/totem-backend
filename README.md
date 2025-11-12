# Backend do Projeto TecnoGuia

Escolha uma das opções abaixo para rodar o projeto:

# Requisitos

Escolha a opção com a qual você deseja rodar a aplicação e instale o que é necessário para essa opção:

### 🐳 Opção 1: Docker

Instale: [https://www.docker.com/products/docker-desktop/](https://www.docker.com/products/docker-desktop/)

### ☕ Opção 2: Modo Dev local com Java

Instale: [https://www.oracle.com/br/java/technologies/downloads/](https://www.oracle.com/br/java/technologies/downloads/)


# Executando com Docker (Opção 1)

Se você quer rodar com Docker, leia o tutorial abaixo. Se não, pule para a section **"Executando localmente com Java"**.

1. Criar um arquivo .env na raiz do projeto

```bash
SPRING_PROFILES_ACTIVE=prod
DATABASE_URL=jdbc:postgresql://<host>:5432/postgres?user=<usuario>&password=<senha>
```

Para acessar o conteúdo que você deve colocar nesse .env, [clique aqui](https://brpucrs-my.sharepoint.com/:w:/r/personal/ashiley_bianca_edu_pucrs_br/Documents/env%20backend.docx?d=w22990f9f2cf24817bfde9c8a29314424&csf=1&web=1&e=0e0mE2) e peça acesso.

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


# Executando localmente com Java (Opção 2 - modo dev)

1. Rodar com Maven Wrapper. No terminal, dentro da pasta totem-backend, execute:

```bash
./mvnw spring-boot:run
```

Isso iniciará o projeto em modo dev, utilizando o banco de dados H2 em memória por padrão.

2. Acessar a aplicação

Abra o navegador e vá para: [http://localhost:8080](http://localhost:8080)
