**Backend do Projeto de Token na recepção do Tecnopuc**

## Sobre o Projeto [EM ANDAMENTO]
API desenvolvida em Spring Boot para gerenciamento de tokens, rotas, mapas e relatórios na recepção do Tecnopuc.

## Requisitos
- Java 17 ou superior
- Maven 3.8+ (ou use o Maven Wrapper: `./mvnw` ou `mvnw.cmd`)
- PostgreSQL (opcional, para persistência de dados)

## Como rodar o projeto
1. Clone o repositório:
	```
	git clone <url-do-repositorio>
	```
2. Entre na pasta do projeto:
	```
	cd totem-backend
	```
3. Execute a aplicação:
	- Com Maven instalado:
	  ```
	  mvn spring-boot:run
	  ```
	- Ou com Maven Wrapper (se configurado):
	  ```
	  ./mvnw spring-boot:run
	  ```
4. Acesse a aplicação - Exemplo para /maps
   ```
   localhost:8080/maps/
   ```

## Configuração do Banco de Dados
Edite o arquivo `src/main/resources/application.properties` para configurar o acesso ao banco PostgreSQL, por exemplo:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/totemdb
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
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
