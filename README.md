# Simple Mobile Place Order System

This is a Spring Boot (Java) REST API project for a simple mobile place order system. The API supports two main use cases:

1. Product Listing
2. Order Cart Management

## Features
1. CRUD API for Product with pagination.
2. API for adding new products to the order cart.
3. API for managing the order cart: displaying the cart and placing orders.
4. Follows REST API design best practices.
5. 

## Technologies
* Java 17
* Spring Boot 3.3.0
* Spring Data JPA
* H2 Database
* Gradle
* Lombok

## Setup and Installation
1. Clone the repository:
```https://github.com/zainnn21/ordersystem.git```
2. Build the project: ```mvn clean install```
3. Run the application: ```mvn spring-boot:run```
   
The application will start on http://localhost:8080.

## API Endpoints
### Product Endpoints
* Create Product ```POST /api/products```
  + Body
```
{
    "name": "MacBook Pro",
    "type": "Laptop",
    "price": 15000000.0,
    "quantity": 5
}
```
* Get Products with Pagination
```GET /api/products?page=0&size=10```
* Get Product by ID ```GET /api/products/{id}```
* Update Product ```PUT /api/products/{id}```
  + Body
```
{
    "name": "Updated MacBook Pro",
    "type": "Laptop",
    "price": 16000000.0,
    "quantity": 10
}
```

* Delete Product ```DELETE /api/products/{id}```

### Order Cart Endpoints
* Create Order Cart ```POST /api/order-cart```
  * Body
```
{
  "customer": "TomJerry",
  "address": "Jl. Tali 7 No.9, Jakarta Barat"
}
```

* Add Product To Ordercart ```POST /api/order-cart/{orderCartId}/add-product```
* Parameters:
   * productId: ID dari produk yang akan ditambahkan.
   * quantity: Jumlah produk yang akan ditambahkan
   
* Get Order Cart by ID ```GET /api/order-cart/{id}```

* Delete Order Cart ```DELETE /api/order-cart/{id}```

## Testing with Postman
1. Start the Spring Boot application.
2. Open Postman and create requests for each endpoint as shown in the API Endpoints section.
3. Verify the responses and ensure the data is correctly processed and stored in the database.
## Contributing
Contributions are welcome! Please open an issue or submit a pull request.

