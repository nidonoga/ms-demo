[![Docker](https://github.com/nidonoga/ms-demo/actions/workflows/docker-publish.yml/badge.svg)](https://github.com/nidonoga/ms-demo/actions/workflows/docker-publish.yml)

# Spring Boot Microservices Application

Este repositório contém uma aplicação baseada em **Spring Boot 3.3.4**, projetada para demonstrar uma arquitetura de microsserviços.

## 🛠️ Tecnologias Utilizadas

- **Spring Boot 3.3.4**: Framework principal para a construção da aplicação.
- **Eureka Server Discovery**: Serviço de registro e descoberta de microsserviços.
- **Spring Cloud API Gateway**: Proxy para roteamento dinâmico de chamadas e integração com o Eureka.
- **OpenFeign**: Cliente HTTP declarativo para comunicação entre microsserviços.
- **Circuit Breaker (Resilience4j)**: Implementação de resiliência para lidar com falhas entre serviços.
- **Spring Actuator**: Monitoramento e métricas da aplicação.
- **Kafka**: Sistema de mensageria para comunicação assíncrona.
- **RabbitMQ**: Sistema de mensageria para filas tradicionais.
- **Docker**: Containerização para facilitar o deploy e a escalabilidade.
- **MySql**: Base de dados para armazenar informações.

## 🎯 Funcionalidades

- Registro e descoberta de serviços com **Eureka**.
- Balanceamento de carga automático utilizando o **Load Balancer** do Spring Cloud.
- Comunicação entre serviços via **OpenFeign**.
- Roteamento e autenticação centralizada com o **API Gateway**.
- Tolerância a falhas com **Resilience4j**.
- Monitoramento e métricas via **Actuator**.
- Integração com sistemas de mensageria: **Kafka** e **RabbitMQ** para comunicação assíncrona.
- Containerização com **Docker**.

## 📂 Estrutura do Projeto

- **naming-server:** Contém o servidor de registro utilizando Euraka.
- **api-gateway:** Configuração do Spring Cloud API Gateway.
- **email-service:** Exemplo de mirosserviço que simula um servicor para envio de emails. 
  - Recebe requisições sincronas via http e assincronas via Kafka e RabbitMQ.
- **user-service:** Exemplos de microsserviço que simula um cadastro de usuário.
  - Faz requisições síncronas e, em caso de falhas, aciona o método fallback para postar nas filas assíncronas.
- **docker-compose.yml:** Arquivo para orquestração de containers Docker.

## 🚀 Como Executar o Projeto

Esta aplicação utiliza uma base de dados MySql e também servidores de mensagens Kafka e RabbitMQ, portanto recomendo subir o projeto com o Docker utilizando o arquivo docker-compose que está na raiz do projeto. Desta forma toda a configuração será feita de forma automática.

- Instale o Docker Desktop: 
    ```
    https://www.docker.com/products/docker-desktop/
    ```
- No diretório onde está localizado o arquivo docker-compose abrir um terminal e executar o seguinte comando:
    ```
    docker-compose up -d
    ```

- Para encerrar os contêineres:
    ```
    docker-compose down
    ```

## 🔦 Links
**Apigateway:**
http://localhost:8765/

**user-service:**
http://localhost:8100/

**email-service:**
http://localhost:8200/

**naming-server:**
http://localhost:8761/

**Actuator:**
http://localhost:8081/actuator/



## 👓 Operações disponíveis

- Listar usuários com paginação
  - GET: localhost:8300/person-service

- Recuperar usuário por id:
  - GET: localhost:8300/person-service/<UUID>

- Excluir usuário:
  - GET: localhost:8300/person-service/<UUID>

- Crair usuário:
  - POST: localhost:8300/person-service
  - Content-Type: application/json"
  - Body:

```
    {
      "login": "user",
      "email": "user@example.com",
      "name": "User Name"
    }
```

- Alterar usuário:
  - PUT: localhost:8300/person-service/<UUID>
  - Content-Type: application/json"
  - Body:

```
    {
      "login": "user",
      "email": "user@example.com",
      "name": "User Name"
    }
```



