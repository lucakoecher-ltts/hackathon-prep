# hackathon-hustle
With this project the user can activate the ECO Mode of a vehicle.

## Technologies Used and Their Purpose

This backend service is designed as a microservice-based architecture that enables communication with a vehicle via MQTT and exposes collected data through RESTful HTTP endpoints. Each technology below has been carefully selected to ensure scalability, reliability, and maintainability.

### Core Technologies

- **Java 17**  
  The service is built on Java 17.

- **Spring Boot 3.2.0**  
  Spring Boot streamlines application setup and deployment, offering a convention-over-configuration approach. With easy to use dependency management.

- **Microservices Architecture**  
  The system follows a microservices pattern to isolate services and enhance scalability. Each component (e.g., data ingestion, API layer) can evolve or scale independently, making the system more maintainable and stable.

### Data Management

- **PostgreSQL**  
  PostgreSQL is used as the primary database for persistent data storage.

- **H2 Database**  
  H2 serves as an in-memory database for testing and local development. It was choosen as it integrates easily with Spring Boot.

### Communication and API Layer

- **MQTT**  
  MQTT (Message Queuing Telemetry Transport) is used for lightweight, reliable communication between the vehicle and the backend. It’s ideal for real-time data transfer over constrained networks.

- **OpenAPI Specification & Swagger UI**  
  The API endpoints are documented using the OpenAPI Specification. This can be viewed and tested through the integrated Swagger UI.

### Monitoring and Testing

- **Spring Boot Actuator**  
  Actuator endpoints provide insights into the application’s health, metrics, and environment.

- **Spring Boot Test**  
  Used for comprehensive integration and unit testing, ensuring service reliability and regression safety. It supports H2 for isolated test environments and simplifies dependency injection during testing.


