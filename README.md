**Backend do Projeto do TecnoGuia**

## Sobre o Projeto [EM ANDAMENTO]

API desenvolvida em Spring Boot para gerenciamento de mapas, rotas e empresas do projeto do TecnoGuia.

## Requisitos

- Java 21 [https://www.oracle.com/br/java/technologies/downloads/](https://www.oracle.com/br/java/technologies/downloads/)
- PostgreSQL 17.6 [https://www.postgresql.org/download/](https://www.postgresql.org/download/)

## Como rodar o projeto

1. Clone o repositório:
	```
	git clone <url-do-repositorio>
	```

2. Entre na pasta do projeto:
	```
	cd totem-backend
	```

3. Iniciar Banco de Dados
   
Necessário estar configurado antes, veja o trecho "Configuração do Banco de Dados" 
	```
	net start postgresql-x64-17
	```

	Ou via Menu Iniciar do Windows

1. Execute a aplicação:
	```
	./mvnw spring-boot:run
	```

2. Acesse a aplicação, você será direcionado ao endpoint de documentação
   ```
   localhost:8080
   ```

## Configuração do Banco de Dados

Acesse `src\resources\application.properties` e altere os dados de acesso:

```
spring.datasource.username=postgres
spring.datasource.password=postgres
```

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
