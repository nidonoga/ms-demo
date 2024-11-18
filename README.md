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
