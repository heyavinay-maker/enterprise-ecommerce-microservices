# ShopCraft: Event-Driven Distributed Microservices Retail Platform

A high-throughput, enterprise-ready e-commerce backend platform built to demonstrate asynchronous service orchestration, decoupled state synchronization, and multi-database environment operations.

## 🏗️ System Architecture Topology

The application drops a monolithic design in favor of an event-driven architecture, using Apache Kafka as an asynchronous data delivery conduit to eliminate tight service coupling:

```text
       [ Postman / Client Payload ]
                   │
                   ▼ (REST HTTP POST)
         [ 🟢 order-service ] ────(Persists)────> [ Oracle 23c DB Free ]
                   │                               (Port 1521 / Sequence Gen)
                   │ (Maps Order Data to DTO Event)
                   ▼
     [ 🔵 Apache Kafka Broker ] ──(Topic: stock-update-topic)──┐
       (Port 9092 via Docker)                                  │
                                                               ▼
         [ 🟢 product-service ] <────(Consumes Event)──────────┘
                   │
                   ▼ (State Delta Updates)
         [ MySQL 8.0 DB Container ]
           (Port 3307 / Delta Stock Calculation)
```

## 🛠️ Core Tech Stack & Specifications
* **Core Backend Core**: Java 21, Spring Boot 3.x, Spring Data JPA, Jakarta Persistence API
* **Distributed Message Streams**: Apache Kafka 7.5.0, Apache Zookeeper (Distributed Coordinator)
* **Storage Engines**: MySQL 8.0 (Product Catalog Management), Oracle Database 23c Free (Transactional Audits)
* **Utilities & Tooling**: Docker Desktop, Eclipse IDE, Postman API Framework, curl Engine, Lombok Compiler

## 🔄 Decoupled Architecture Lifecycle Loop
1. **Transaction Entry**: Clients submit an order payload containing a unique product mapping ID and a custom `purchaseQuantity` parameter.
2. **Oracle Sequence Persistence**: The `order-service` processes the record and allocates keys utilizing low-latency database Sequence Generators (`GenerationType.SEQUENCE`).
3. **Asynchronous Broadcast**: The system maps local entity states into a shared data structure (`OrderPlacedEvent`) and broadcasts it downstream over Kafka instantly.
4. **Reactive Inventory Deduction**: The `product-service` intercepts the package packet via an open `@KafkaListener` thread, runs thread-safe math calculations, and updates your MySQL inventory fields under transactional safety limits (`@Transactional`).

## 🚀 Local Infrastructure Container Setup
Execute these commands to boot up your container engine topologies side-by-side:

```bash
# 1. Boot Distributed Coordinator Engine
docker run -d --name zookeeper-container -p 2181:2181 -e ZOOKEEPER_CLIENT_PORT=2181 confluentinc/cp-zookeeper:7.5.0

# 2. Boot Apache Kafka Message Stream Broker
docker run -d --name kafka-container -p 9092:9092 -e KAFKA_BROKER_ID=1 -e KAFKA_ZOOKEEPER_CONNECT=localhost:2181 -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 confluentinc/cp-kafka:7.5.0

# 3. Boot Core Relational Microservices Databases
docker start oracle-container
docker start mysql-container
```

## 🏁 Enterprise Verification Verification
```bash
# Query the live inventory balance endpoint to verify Kafka delta propagation
curl -X GET http://localhost:8084/api/products
```
