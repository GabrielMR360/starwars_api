<h1 align="center">
    Star Wars Resistence Social Network API
</h1>

## Sobre

O projeto foi feito para a resolução de um problema/desafio em que o objetivo é desenvolver uma API para
compartilhar recursos entre os rebeldes.

## Tecnologias utilizadas

Foi utilizado as seguintes tecnologias e ferramentas:

- Java
- Spring Boot
- Spring Data
- Flayway
- Maven
- MySQL
- Swagger
- Docker

## Funcionalidades

- Adicionar rebeldes
- Atualizar localização de um rebelde
- Reportar um rebelde como traidor
- Negociar itens
- Relatórios:
    - Porcentagem de traidores
    - Porcentagem de rebeldes
    - Quantidade média de cada recurso por rebelde
    - Pontos perditos devido a traidores

## Como rodar

### Docker

Para rodar o projeto é necessário ter o Maven para buildar o projeto e o Docker para subir o banco de dados e a aplicação.

```
mvn clean package -Dmaven.test.skip=true
```

Antes de rodar o comando do docker-compose pode ser necessário dar permissão para o arquivo de script para aguardar o banco de dados subir por completo.

```
chmod +x /app/wait-for-it.sh
```

```
docker-compose up -d --build
```

### Localmente

Para rodar localmente é necessário alterar o arquivo application.properties para apontar para o banco de dados local, alterando o host da url do banco para localhost e o usuário e senha para o do banco de dados local configurado.

## Documentação

A documentação da API foi feita utilizando o Swagger, para acessar a documentação basta rodar o projeto e acessar o link:

```
http://localhost:8080/swagger-ui.html 
```