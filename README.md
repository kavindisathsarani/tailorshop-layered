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
