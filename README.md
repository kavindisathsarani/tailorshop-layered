# TailerMater TailorShop Layered

**TailorShop Layered** is an academic Java application developed using the **Layered Architecture** design pattern, with the user interface created via **JavaFX Scene Builder** for a clean separation of design and logic.

## Overview

TailorShop Layered assists users in [brief description—e.g., managing tailoring orders, handling materials, recording measurements, managing garments, etc.]. The application promotes modular design by separating concerns into different layers, ensuring scalability, maintainability, and clarity.

## Built With

- **Java** – Core programming language for application logic  
- **JavaFX** – Used for building desktop UI  
- **JavaFX Scene Builder** – Visual layout tool used for designing FXML-based UI forms  
- **Layered Architecture** – Organizes the system into layers: Entity, Repository, Service, and Controller  

## Architecture Overview

```text
+---------------------+       +---------------------+       +---------------------+       +----------------------+
|       Entity        | <---> |     Repository      | <---> |      Service        | <---> |      Controller      |
| Represents domain   |       | Handles data        |       | Contains business   |       | JavaFX controllers   |
| objects and models  |       | persistence (DB)    |       | logic and rules     |       | & request handling   |
+---------------------+       +---------------------+       +---------------------+       +----------------------+

Entity Layer: Represents application data and domain objects

Repository Layer: Manages data persistence and database operations

Service Layer: Contains business logic and rules, interacting between repository and controller

Controller Layer: Handles UI interactions and coordinates with services

## Project Structure 

TailorShop-Layered/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── entity/        – Domain objects (e.g., Garment, Material, Customer)
│   │   │   ├── repo/          – Interfaces for data access
│   │   │   ├── service/       – Interfaces for business logic
│   │   │   ├── serviceImpl/   – Implementations of business logic
│   │   │   ├── controller/    – JavaFX controllers
│   │   │   └── Main.java      – Application entry point
│   │   └── resources/
│   │       ├── view/          – `.fxml` files created in Scene Builder
│   │       └── styles.css     – Optional stylesheet for UI styling
├── lib/                       – External libraries if any
└── README.md

## Used Technologies

- **Java** – Core programming language (99.8% of the codebase).  
- **JavaFX** – Framework for building the desktop UI with FXML and controllers.  
- **JavaFX Scene Builder** – Visual designer for creating/editing FXML-based interfaces.  
- **Layered Architecture** – Application structure separated into layers:
  - **Entity Layer** – Domain objects and models  
  - **Repository Layer** – Data access and persistence logic  
  - **Service Layer** – Business rules and operations  
  - **Controller Layer** – JavaFX controllers that handle UI events and coordinate interactions  
- **Maven** – Build and dependency management via `pom.xml`.  
- **CSS** – Used for styling the JavaFX UI (approx. 0.2% of project).  


git clone https://github.com/kavindisathsarani/tailorshop-layered.git
cd tailorshop-layered

