# Micorserviço de Cotação de minerios

Este projeto usa Quarkus, o Framework Java Supersônico Subatômico.
Se você deseja saber mais sobre Quarkus, visite o site: https://quarkus.io/.

## Executando a aplicação no modo de desenvolvimento

Você pode executar sua aplicação no modo de desenvolvimento que permite codificação ao vivo usando:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_** Quarkus agora vem com uma interface de usuário de desenvolvimento, disponível apenas no modo de desenvolvimento em http://localhost:8080/q/dev/.

## Empacotando e executando a aplicação

A aplicação pode ser empacotada usando:

```shell script
./mvnw package
```

Isso produz o arquivo `quarkus-run.jar` no diretório `target/quarkus-app/`. 

Observe que não é um _über-jar_ pois as dependências são copiadas para o diretório `target/quarkus-app/lib/`.

A aplicação agora pode ser executada usando `java -jar target/quarkus-app/quarkus-run.jar`.

Se você deseja criar um _über-jar_, execute o seguinte comando:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```
A aplicação, empacotada como um _über-jar_, agora pode ser executada usando `java -jar target/*-runner.jar`.

## Criando um executável nativo

Você pode criar um executável nativo usando:

```shell script
./mvnw package -Dnative
```
Ou, se você não tiver o GraalVM instalado, você pode executar a construção do executável nativo em um contêiner usando:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

Você pode então executar seu executável nativo com: `./target/cotacao-1.0.0-SNAPSHOT-runner`.

Se você deseja saber mais sobre a construção de executáveis nativos, consulte <https://quarkus.io/guides/maven-tooling>.

# Guias relacionados

- Hibernate ORM com Panache (guia): Simplifique seu código de persistência para Hibernate ORM via o padrão de registro ativo ou o padrão de repositório
- Apache Kafka Streams (guia): Implemente aplicações de processamento de fluxos baseadas em Apache Kafka
- Driver JDBC - PostgreSQL (guia): Conecte-se ao banco de dados PostgreSQL via JDBC

## Código fornecido
### Hibernate ORM
Crie sua primeira entidade JPA

[Seção relacionada do guia...](https://quarkus.io/guides/hibernate-orm)

[Seção relacionada do guia Hibernate com Panache...](https://quarkus.io/guides/hibernate-orm-panache)

# Documentação do Docker 

- Configuracao de inicializacao do banco de dados:
```shell script
  docker run -d --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=root postgres 
  docker run -d --name zookeeper -p 2181:2181 zookeeper:3.4.9 
  docker run -d --name kafka -p 9092:9092 --link zookeeper:zookeeper wurstmeister/kafka:2.12-2.2.1 
```

