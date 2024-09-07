# Desafio de Programação

Este projeto tem como intuito, importar dados através de upload de arquivo e persistir na base de dados.

### *Principais ferramentas*

**Linguagem Principal:** Java (JDK 21)  
**Framework:** Spring Boot 3  
**Banco de Dados:** PostgreSQL  
**Migrações do Banco de Dados:** Flyway  
**Gerenciador de Dependências/Build:** Gradle  

### *Configuração do Ambiente de Desenvolvimento*

###### JAVA
Baixar e instalar o Java JDK versão 21 [DOWNLOAD](https://www.oracle.com/br/java/technologies/downloads/#java21)  

###### POSTGRESQL

Utilizar o docker compose para criar um container PostgreSQL local. Na raiz do projeto existe um arquivo `docker-compose.yml` que configura o banco de dados para ser utilizado localmente, basta executar o comando: 
```sh
$ docker-compose up -d
```

###### Variáveis de Ambiente do Projeto
Para executar o projeto, precisaremos de preencher algumas variáveis de ambiente. Existe um arquivo modelo chamado `.env.sample`, nele contém as variáveis que precisam ser preenchidas. Basta duplicar esse arquivo e renomeá-lo para `.env` que o Spring Boot vai procurar os valores e preencher no arquivo `application.properties`. Exemplo de preenchimento:  

```.env
DESAFIO_PROGRAMACAO_API_DB_URL=jdbc:postgresql://host:port/db-name
DESAFIO_PROGRAMACAO_API_DB_USER=db-user
DESAFIO_PROGRAMACAO_API_DB_PWD=db-password
DESAFIO_PROGRAMACAO_JPA_SHOW_SQL=true
DESAFIO_PROGRAMACAO_API_SECRET=my-secret
```

###### Teste das API's

O endpoint que recebe o upload do arquivo de vendas é protegido, para executar ele é necessário um token de acesso.
Para gerar o token basta cadastrar um usuário `/users/create`, gerar um token através do login `/users/login` e depois anexar o token na requisição de upload `/sales`.

> OBS.: Na pasta docs, existe as curl's para esses endpoints, assim é possível importar em algumas ferramentas de teste de API como Postman, Insomnia, etc.
  
