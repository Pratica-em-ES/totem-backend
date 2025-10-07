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

## Alteração

99a

24.2656 m -> 0
25.1045 m -> 0
4.05808 m ==

97

25.5631 m -> 0
-24.8131 m -> 0
2.76042 m

96j

-17.617 m
8.56175 m
8.14425 m

96bcdf

8.02109 m
13.1692 m
2.02154 m

96a

4.19855 m
30.0202 m
1.18144 m

96

-10.8675 m
27.1946 m
2.5255 m

95c

29.3427 m
-8.6717 m
6.94863 m

95 a

16.5603 m
-0.00469 m
1.47842 m

94

4.47708 m
-18.1663 m
1.6764 m

93

3.19291 m
-34.543 m
0

92a

-22.0325 m
-28.7299 m
1.18144 m

91b

4.19855 m
-5.92136 m
1.18144 m

91a

-19.6994 m
-11.3027 m
1.18144 m

tecnopuc

38.6166 m
30.0898 m
1.61439 m