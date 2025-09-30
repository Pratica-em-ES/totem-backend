**Backend do Projeto do TecnoGuia**

## Sobre o Projeto [EM ANDAMENTO]

API desenvolvida em Spring Boot para gerenciamento de mapas, rotas e empresas do projeto do TecnoGuia.

## Requisitos

- Java 21 [https://www.oracle.com/br/java/technologies/downloads/](https://www.oracle.com/br/java/technologies/downloads/)
- PostgreSQL 17.6 [https://www.postgresql.org/download/](https://www.postgresql.org/download/)
- Docker Desktop [https://www.docker.com/products/docker-desktop/](https://www.docker.com/products/docker-desktop/)

## Como executar

1. Criar arquivo .env

```bash
# dev usa H2 Database
SPRING_PROFILES_ACTIVE=prod
DATABASE_URL=jdbc:postgresql://<cloud_address>/postgres?user=<docker_user>&password=<password>
```

2. Iniciar o Docker Desktop via Menu Iniciar. Você deve ver um "service is runnig"

3. Executar usando o Docker

```bash
docker-compose up --build
```

4. Acessar [[localhost](http://localhost:8080/)](http://localhost:8080/)

## Endpoints principais

- `GET /map/` - Retorna o mapa com os prédios e com as ruas

## Autores

Bernardo Nilson  
Endrew Soares  
Guilherme Hoffmann  
Luana Sostisso  
Lucas Santos  
Maurício Alcântara  
Thaysa Roberta