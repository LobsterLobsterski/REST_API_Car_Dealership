# Car Dealership REST API

Welcome to the Car Dealership REST API project! This Spring Boot application provides a robust and efficient solution for managing a car dealership's inventory through a RESTful API. Whether you're a car enthusiast, a dealership owner, or a developer looking to learn, this project offers a comprehensive example of building a REST API using Spring Boot.

## Features

- **Manage Inventory:** Creade, Read, Update and Delete cars and their manufacturers in the dealership.
- **Pagination support:** Choose the specific amount and type of data retrieved from data base with one method!


## Technologies Used

- **Spring Boot:** A powerful and flexible framework for building Java-based enterprise applications.
- **Spring Data JPA:** Simplifies data access and persistence using Java Persistence API (JPA).
- **Database:** Utilizes a relational database system (PostgreSQL) to store car and manufacturer data.

## How to Use

1. **Clone the Repository:**
git clone https://github.com/yourusername/car-dealership-api.git

3. **Configure the Database:**
- Set up your database configuration in `application.properties` or `application.yml`.
  - a docker-compse.yml is provided, use "docker-compse up" in terminal to set up a dockerized database   
- Run database migrations and seed initial data if necessary.

3. **Build and Run the Application:**
  
5. **Explore the API:**
- Access Swagger documentation at `http://localhost:8080/swagger-ui.html` to understand and test available endpoints.
- Use tools like Postman or `curl` commands for API testing.

## Contributing

Contributions are welcome! If you find issues or have suggestions, please create a GitHub issue and submit a pull request. Your feedback helps me improve this project and thus my knowledge.

## License

This project is licensed under the [MIT License](LICENSE), making it open and accessible for everyone.

Thank you for using the Car Dealership REST API! Happy coding! 🚗✨
