# EventSphere - Command Line Interface


EventSphere is a full-stack application with a Command Line Interface (CLI) component designed to manage configuration parameters, simulate event operations, and maintain system user data. Developed using core Object-Oriented Programming (OOP) principles, the application connects to a MySQL database for robust and persistent data storage.

## Table of Contents
- [EventSphere - Command Line Interface](#eventsphere---command-line-interface)
  - [Table of Contents](#table-of-contents)
  - [Features](#features)
  - [Technologies Used](#technologies-used)
  - [Installation and Setup](#installation-and-setup)
    - [Prerequisites](#prerequisites)
    - [Setup Instructions](#setup-instructions)
  - [Usage](#usage)
  - [License](#license)

---

## Features

1. **Add Configuration Parameters**
2. **Start Simulation**
3. **Stop Simulation**
---

## Technologies Used

- **Java**: Core programming language.
- **MySQL**: Relational database management system.
- **JDBC**: Java Database Connectivity for database interaction.
- **Maven**: Build automation tool for managing dependencies and builds.

---

## Installation and Setup

### Prerequisites
- **Java Development Kit (JDK 17 or higher)**
- **MySQL Server**
- **IDE (ex: IntelliJ IDEA, Eclipse, Visual Studio Code)**

### Setup Instructions

1. **Clone the Repository**  
   Clone the EventSphere repository from GitHub:
   ```bash
   git clone https://github.com/yrgamage/EventSphere.git
   ```
2. **Navigate to the Command Line Interface Folder**
   ```bash
   cd EventSphere/command_line_interface_project
   ```
   
3. **Import the Project in Your IDE**
    - Open your preferred Java IDE (ex: IntelliJ IDEA, Eclipse, Visual Studio Code).
    - Import the project as a Maven project.


4. **Configuring the MySQL Database**
   - Update the database connection details in the `DatabaseConnector.java` file to reflect your MySQL setup:
   ```java
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_NAME = "DATABASE_NAME";
    private static final String FULL_URL = URL + DATABASE_NAME + "?createDatabaseIfNotExist=true";
    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";
   ```
    - Steps:
        - Replace the `USERNAME`, `PASSWORD` and `DATABASE_NAME ` fields in the `DatabaseConnector.java` file.
        - The database will be created automatically if it does not already exist due to the `?createDatabaseIfNotExist=true` parameter.


5. **Run the Application**
    - Build the project using Maven.
    - Run the `Main.java` file to launch the application.

---

## Usage

1. Launch the application by running the `Main` class.
2. Follow the on-screen menu to perform operations:
    - Add configuration parameters.
    - Start simulations based on the users.

---

## License

This project is licensed under the [MIT License](LICENSE).