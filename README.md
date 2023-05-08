# Chat Application
This is a chat application implemented using Java and Spring Boot. The purpose of this project was to demonstrate the usage of as many architectural patterns as possible from Martin Fowler's book, "Patterns of Enterprise Application Architecture". It was created during the ***Advanced Design and Architectural Patterns*** course at Jagiellonian University.

## Architecture Patterns
This project uses several popular architecture patterns, including:

- **Model-View-Controller (MVC)**: This pattern separates the application into three parts: the model (data and business logic), the view (presentation layer), and the controller (handles user input and updates the model and view).
- **Data Access Object (DAO)**: This pattern provides an interface between the application and the database, allowing for CRUD (create, read, update, delete) operations to be performed without knowing the underlying details of the database.
- **Service Layer**: This pattern abstracts the business logic and data access from the controller, allowing for easier maintenance and testing.
- **Data Mapper**: This pattern maps the database schema to objects in the application, allowing for more flexibility in data storage and retrieval.
