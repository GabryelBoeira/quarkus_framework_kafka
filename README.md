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

# Certificado de Conclusão:
- Certificado Udemy: [acessar](https://www.udemy.com/certificate/UC-fdac2a40-f05d-4ede-b8c7-acb4f2c8d76f/)

# Detalhes da execução do projeto:
## Informações sobre cada projeto
**Para acessar localmente [Cotacao](http://localhost:8080/q/dev-ui/welcome)**

1. **Projeto Cotação:**

O microserviço **Cotação** é responsável por buscar a cotação do dólar periodicamente e enviar mensagens para o
sistema utilizando o **Apache Kafka**.

**Funcionamento principal:**
- O serviço realiza requisições periódicas a uma API externa para obter a cotação atualizada do dólar.
- Após obter os dados da cotação, ele organiza-os numa mensagem no formato JSON (ou outro modelo estruturado).
- Em seguida, publica essa mensagem num tópico configurado no **Kafka**, possibilitando que outros microserviços
  ou sistemas interessados consumam os dados em tempo real.

**Tecnologias utilizadas:**
- **Quarkus**: Para o desenvolvimento do microserviço, aproveitando a alta produtividade e suporte nativo para
  Kafka.
- **Apache Kafka**: Como middleware para comunicação baseada em eventos.

**Requisitos de configuração:**
- Configurações de periodicidade para as requisições de cotação.
- Integração com o **Kafka** (produtor) detalhando o tópico onde a mensagem será publicada.

**Fluxo básico:**
1. Scheduler realiza uma chamada para obter a cotação do dólar.
2. Dados são processados e formatados.
3. Mensagem é enviada ao broker Kafka num tópico específico.

Este microserviço é crucial para fornecer informações atualizadas sobre o mercado cambial e facilitar a comunicação
por eventos assíncronos.

## 2. **Projeto Proposta:**
**Para acessar localmente [Proposta](http://localhost:8091/q/dev-ui/welcome)**

O microserviço **Proposta** é responsável por receber dados provenientes de mensagens enviadas por outros sistemas
através do **Apache Kafka**, processar essas informações e disponibilizar os dados organizados.

**Funcionamento principal:**

- Consome mensagens específicas de um tópico no **Kafka**.
- Processa os dados recebidos, aplicando as regras de negócio necessárias.
- Persiste as informações no banco de dados `proposaldb`.

**Tecnologias utilizadas:**

- **Quarkus**: Para o desenvolvimento do microserviço, com foco em produtividade e suporte nativo para Kafka.
- **Apache Kafka**: Para comunicação assíncrona baseada em eventos.
- **PostgreSQL**: Banco de dados para armazenamento persistente.

**Requisitos de configuração:**

- Configurações do consumidor de mensagens no tópico Kafka.
- Integração com o PostgreSQL para persistência de dados.

**Fluxo básico:**

1. Consumidor Kafka lê mensagens de um tópico.
2. Dados são processados e tratados de acordo com regras de negócio.
3. Informações são persistidas no banco de dados `proposaldb`.

Este microserviço é essencial para gerenciar o fluxo de propostas, garantindo a consistência e persistência das
informações recebidas.

---

## 3. **Projeto Relatório:**
**Para acessar localmente [Relatorio](http://localhost:8081/q/dev-ui/welcome)**

O microserviço **Relatório** é utilizado para agregar dados processados por outros microserviços, realizando análises e
disponibilizando informações consolidadas.

**Funcionamento principal:**

- Lê dados de múltiplos tópicos no **Kafka** ou diretamente de bases de dados.
- Realiza o processamento e agregação dos dados.
- Disponibiliza relatórios personalizados por meio de APIs REST.

**Tecnologias utilizadas:**

- **Quarkus**: Para o desenvolvimento de APIs REST, focado em desempenho e usabilidade.
- **Apache Kafka**: Como meio de consumir dados.
- **PostgreSQL**: Para persistência de informações provenientes do banco de dados `reportdb`.

**Requisitos de configuração:**

- Integração com múltiplos tópicos no **Kafka** (consumidor).
- Configuração de API REST para exposição de relatórios formatados.
- Integração com o banco de dados `reportdb`.

**Fluxo básico:**

1. Consumidor Kafka ou conexões diretas em bancos de dados buscam informações.
2. Dados são processados, agregados e analisados.
3. Resultado é exposto via endpoints REST.

Este microserviço desempenha um papel crucial ao centralizar e simplificar análises de dados provenientes de outros
serviços, criando relatórios valiosos para decisões de negócio.

## 4. **Projeto Gateway-BFF:**
**Para acessar localmente [Gateway-BFF](http://localhost:8095/q/dev-ui/welcome)**

O microserviço **Gateway-BFF** (Backend for Frontend) atua como um ponto único de entrada para os serviços backend,
abstraindo e simplificando as interações entre o frontend e os microsserviços.

**Funcionamento principal:**

- Encaminha as solicitações recebidas do frontend para os outros microsserviços internos.
- Faz orquestração de chamadas para consolidar dados provenientes de múltiplos microsserviços, devolvendo ao cliente uma
  resposta agregada.
- Trata autenticação e autorização, garantindo acesso seguro às APIs.

**Tecnologias utilizadas:**

- **Quarkus**: Para desenvolvimento ágil e performático das APIs.
- **RESTEasy Reactive**: Para criar os endpoints REST de alta performance.
- **Keycloak**: Para autenticação e autorização via OAuth2/OpenID Connect.
- **Apache Kafka**: Para interação assíncrona, quando necessário.

**Requisitos de configuração:**

- Configuração de conexões para os microsserviços internos.
- Integração com **Keycloak** para validação de tokens e gerenciamento de autenticação.
- Configuração de endpoints REST que representam funcionalidades de integração.

**Fluxo básico:**

1. O frontend realiza uma requisição ao **Gateway-BFF**.
2. O Gateway valida o token de autenticação com o **Keycloak**.
3. Com base na requisição, o Gateway chama um ou mais microsserviços para obter os dados necessários.
4. Resposta consolidada é enviada para o frontend.

Este microserviço desempenha um papel essencial ao centralizar o acesso ao backend, simplificar integrações para o
frontend e garantir segurança nas comunicações.

## Imagens Docker para PostgresSQL e Keycloak

1. Configuracao do PostGres:

   ```shell script
   docker run -d --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=root -d postgres
   ```

2. Configuracao do KeyCloak:

   ```shell script
   docker run --name keycloak -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin -p 8180:8080 quay.io/keycloak/keycloak:17.0.1 start-dev
   ```

3. Adicionar configurar quarkus-realm.json
   - Arquivo para importar no KeyCloak [quarkus-realm.json](quarkus-realm.json) esta na raiz do projeto.

## Adicionando Bancos de Dados ao PostgresSQL no Docker

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

## Adicionando Jaeger Tracing com Docker
**Para acessar localmente [jaeger](http://localhost:16686/search)**

```bash
  docker run -p 5775:5775/udp -p 6831:6832/udp -p 5778:5778 -p 16686:16686 -p 14268:14268 jaegertracing/all-in-one:latest 
```

