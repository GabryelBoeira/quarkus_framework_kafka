# Quarkus Framework com Kafka - Udemy

<p>
Este curso tem por objetivo mostrar todo o potencial e produtividade do desenvolvimento de software backend com a plataforma Java e o Framework Quarkus, usando a comunicação via eventos com o Apache Kafka e rastreabilidade de funcionamento dos microserviços usando a ferramenta Jaeger.
</p>
<p>
Todo o acesso externo da aplicação web backend vai passar por um protótipo de gateway que também seguirá o conceito básico de Backend For Frontend (BFF), esta será a camada principal de acesso.
</p>

# Principais tópicos cobertos neste curso:

- Desenvolvimento de Micrserviços com Java e Quarkus e Maven;
- Comunicação via API's REST;
- Comunicação via eventos com Apache Kafka;
- Rastreabilidade de microserviços com Jaeger Tracing;
- Segurança de aplicações web com Keycloak;
- Propagação de tokens JWT;
- Docker
- DevContainers
- Swagger UI (OpenAPI)

# Imagens Docker para PostgreSQL e Keycloak

1. Configuracao do PostGres:

    ```shell script
    docker run -d --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=root -d postgres
    ```

2. Configuracao do KeyCloak:

    ```shell script
    docker run --name keycloak -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin -p 8180:8080 quay.io/keycloak/keycloak:latest start-dev
    ```

## Adicionando Bancos de Dados ao PostgreSQL no Docker

Este guia demonstra como adicionar os bancos de dados `quotationdb`, `proposaldb` e `reportdb` à sua instância PostgreSQL que está rodando em um container Docker.

**Pré-requisitos:**

- Docker instalado e em execução.
- Um container PostgreSQL rodando com o nome `postgres` (como iniciado com o comando: `docker run -d --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=root -d postgres`).

**Passos:**

1.  **Acesse o shell do container PostgreSQL:**

    Abra seu terminal e execute o seguinte comando para entrar no shell do container `postgres`:

    ```bash
    docker exec -it postgres bash
    ```

2.  **Conecte-se ao PostgreSQL usando o cliente `psql`:**

    Dentro do shell do container, utilize o comando `psql` para conectar-se ao servidor PostgreSQL como o usuário padrão `postgres`:

    ```bash
    psql -U postgres
    ```

    Você deverá ver um prompt como `postgres=#`, indicando que a conexão foi estabelecida.

3.  **Crie os novos bancos de dados:**

    No prompt do `psql`, execute os seguintes comandos SQL para criar os bancos de dados desejados:

    ```sql
    CREATE DATABASE quotationdb;
    CREATE DATABASE proposaldb;
    CREATE DATABASE reportdb;
    ```

    Após cada comando, você deverá receber uma confirmação como `CREATE DATABASE`.

4.  **Saia do `psql`:**

    Para sair do cliente `psql`, digite o seguinte comando e pressione Enter:

    ```sql
    \q
    ```

5.  **Saia do shell do container:**

    Para retornar ao seu terminal principal, digite o seguinte comando e pressione Enter:

    ```bash
    exit
    ```

**Verificação:**

Para verificar se os bancos de dados foram criados com sucesso, você pode se conectar novamente ao `psql` e listar os bancos de dados existentes com o comando `\l`:

```bash
  docker exec -it postgres bash
  psql -U postgres
  \l
  \q
  exit
```
