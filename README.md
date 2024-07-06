
# Electronic Store Ecommerce Backend Application

 **ElectronicStore** is a commerce-based project. In this project, I have created complete REST APIs for the Electronic store.It contains diffrent  modules of the electronic store like User Module, Category Module, Product Module, Cart Module, Order Module,  API Documentation Module, and Deployment using Docker on a cloud module, etc.


## Important Links
- **Swagger API Documentation**: [Click here](http://localhost:9095/swagger-ui/index.html##/)
- **Model/Schema for E_store**: [Click here](https://drive.google.com/file/d/1gPXZf5PzT2OYFCl9T5SjOrg_cW8CwfAc/view)
- 
## Features

### User Module
- **Profile Management**: Update personal details and manage account settings.

### Category Module
- **Category Creation**: Add new categories for products.
- **Category Management**: Update and delete existing categories.

### Product Module
- **Product Addition**: Add new products to the store.
- **Product Management**: Edit and delete existing products.
- **Product Search**: Search for products using various filters.

### Cart Module
- **Add to Cart**: Add products to the shopping cart.
- **Update Cart**: Modify the quantity of items in the cart.
- **Remove from Cart**: Remove items from the cart.
- **View Cart**: View all items in the cart.

### Order Module
- **Place Order**: Place an order for the items in the cart.
- **Order History**: View past orders and their statuses.
- **Order Management**: Update and cancel orders.


### API Documentation Module
- **Swagger Integration**: Document all APIs using Swagger for easy access and testing.

### Deployment
- **Docker**: Use Docker for containerization and deployment on the cloud.

## Technologies Used
- **Java Spring Boot**: Framework for building the backend.
- **MySQL**: Database for storing data.
- **Swagger**: For API documentation.
- **Docker**: For containerization and deployment.



## Installation and Setup

1. Clone the repository:

```bash
git clone https://github.com/shobhit1502/Electronic_Store.git
```

2.Navigate to the project directory:
```bash
cd Electronic_Store
```

3. Set up the database: Make sure you have MySQL installed and running. Create a new database for the project.
   
4. Configure the application: Update the application.properties file with your database credentials and other necessary configurations.
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

```
5.Build the project:
```bash
mvn clean install
```

6.Run the server:
```bash
mvn spring-boot:run
```

Or Else download the zip file from github,unzip it and open it in intellij IDE

This project is designed to provide a comprehensive backend for an electronic store, utilizing Java Spring Boot to ensure a scalable and maintainable system. Explore the documentation for detailed information on each module and to get started with integrating these functionalities into your application.




