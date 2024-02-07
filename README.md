# BCNGroup backend challenge for Inditex

## Description
- This project is a backend challenge for Inditex. It is a REST API that allows to manage a list of products and their prices.
- The instructions and rules of the challenge can be found in the following [link](./readme-resources/docs/code_challenge_bcngroup_inditex.pdf).

## Tech Stack
- IDE: IntelliJ IDEA Community Edition (2023.3)
- Java: 21.0.1 OpenJDK
- Spring Boot: 3.2.2
    - Spring Starter Web
    - Spring Data
    - Spring Boot Validation
    - Spring Boot Test
    - Spring Actuator
    - Spring DevTools
- Lombok: 1.18.30
- SpringDoc OpenApi: 2.3.0
- H2 Database
- Maven: 3.9.2

## Versions
### v1.0.0
- Initial application launch.

## Prerequisites to test/run the application
- Java: 21.0.1 - [Download](https://openjdk.org/projects/jdk/21/)
- Maven: 3.9.2 - [Download](https://maven.apache.org/download.cgi)

## Decisions made
### Adopting Hexagonal Architecture
- We opted for a Hexagonal Architecture to clearly separate the core business logic from the infrastructure layer. This approach enhances maintainability and scalability, allowing independent evolution of the business logic and external components.
### Choosing Java 21
- Java 21, being the latest Long-Term Support (LTS) version, was selected as the programming language for this project. This choice allows us to leverage the newest features and improvements in the Java language, ensuring modern practices and efficient performance.
### Choosing Spring Boot 3.2.2
- Spring Boot 3.2.2 was chosen for its extensive set of features that significantly simplify the development and deployment of web applications. Its autoconfiguration capabilities, along with a wide array of starter kits, allow for rapid development and easy integration of various components. Spring Boot 3.2.0 also aligns well with our use of Java 21, supporting and enhancing the latest features of the language.
### Using Lombok to Reduce Boilerplate
- Lombok was used to reduce boilerplate code and improve the readability of the application. By eliminating the need for repetitive code, Lombok allows developers to focus on the core business logic, resulting in a cleaner and more concise codebase.
### Using SpringDoc OpenApi to Document the API
- SpringDoc OpenApi was used to document the API. This library provides a simple and straightforward way to generate OpenAPI documentation for Spring Boot applications. By leveraging the annotations provided by SpringDoc OpenApi, we can easily document the API and keep the documentation in sync with the codebase.
### Using Spring Actuator to Monitor the Application
- Spring Actuator was used to monitor the application. This library provides a simple and straightforward way to monitor the application's health, metrics, and other useful information. By leveraging the endpoints provided by Spring Actuator, we can easily monitor the application and keep track of its performance.
### Employing Records in Domain Layer
- In the domain layer of our architecture, Java Records are utilized to encapsulate the state and behavior of business entities. This choice underscores our commitment to immutability and clarity within our domain logic. Records provide a succinct and expressive approach to defining our models, significantly reducing the boilerplate often associated with traditional Java POJOs. By leveraging records, we ensure that domain objects are thread-safe and maintain a consistent state, facilitating a more reliable and predictable flow of data towards the application layer.
### Implementing GlobalExceptionHandler
- A GlobalExceptionHandler is utilized to handle exceptions at a global level within the application. By propagating domain exceptions to the application layer and centralizing error handling, we eliminate the need for repetitive try/catch blocks in services. This strategy not only simplifies the codebase but also ensures consistent error handling across the application.
### Use of functional programming
- Functional programming is used to simplify the code and make it more readable. This approach allows us to focus on the core business logic, resulting in a cleaner and more concise codebase.
### Implementing JaCoCo for Code Coverage
- JaCoCo was used to measure the code coverage of the application. This library provides a simple and straightforward way to measure the code coverage of the application. By leveraging the reports provided by JaCoCo, we can easily identify areas of the codebase that are not covered by tests and improve the overall quality of the application.
- The code coverage report can be found in the following path: `target/site/jacoco/index.html`
### Implementing fmt-maven-plugin
- fmt-maven-plugin was used to enforce a consistent code style across the application. This plugin provides a simple and straightforward way to format the codebase according to google styleguide. When the application is built, the plugin automatically formats the codebase, ensuring that the code adheres to the defined style guide.
### Focusing on Clean and Maintainable Code
- Throughout the development process, a strong emphasis was placed on writing clean, well-documented, and maintainable code. This approach not only makes future modifications and extensions more manageable but also ensures that the application adheres to industry best practices.

## To improve
### Considering Advanced Mapping Solutions
- As our application evolves, we are open to adopting more powerful mapping tools like MapStruct, particularly if the API expands significantly. For now, Spring's built-in type conversion is sufficient, but we recognize the potential need for a more sophisticated mapping solution as the complexity of data transformations increases. MapStruct would offer greater efficiency and cleaner code for complex mapping scenarios, making it a valuable addition for future scalability.
### Deploying the Application in Cloud Services
- We plan to deploy the application in cloud services to ensure that the application is always available. This improvement will allow us to have a more reliable and stable application. Additionally, we will focus on implementing comprehensive integration tests to ensure the reliability and stability of the cloud services.
### Improve the code
- We plan to improve the code by refactoring it to follow the best practices and design patterns. This improvement will allow us to have a more maintainable and scalable codebase. Additionally, we will focus on implementing SOLID rules and the application of Domain Drive Design pattern (DDD).
### Improve the tests
- We plan to improve the tests by adding more unit tests, integration tests and CLI tests. This improvement will allow us to have a more reliable and stable codebase.

## Run tests
You can use your favorite IDE to run the tests or use the following command:
```shell
mvn test
```

## Run the application
1. Clone the repository
2. In the cloned folder repository, run the following command:
```shell
mvn spring-boot:run
```
## Validations
> [!WARNING]
> All request parameters are required.
### Date format
- The date format must be in the ISO8601 format: `yyyy-MM-dd'T'HH:mm:ss`.
### Product ID and Brand ID
- Both the product ID and the brand ID must be greater than 0.

> [!CAUTION]
> The data types given in the example for the models have been respected, but perhaps it would be better to place the ids as UUIDs or save the dates as epochs. In any case, all this will depend on the context of the project and the personal opinions of each engineer.

## Available endpoints
Since the project uses SpringDoc OpenApi, we can see the available endpoints at the following link:
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
### Examples to call the endpoints
You can use the HTTP client of your choice to call the endpoints. In the next examples I will use Postman to perform the requests.
Using the [import feature of Postman](https://learning.postman.com/docs/getting-started/importing-and-exporting-data/) you can import the OpenAPI definition from the following link: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)
#### Returns the highest priority price for a given product, brand and date
- `GET /api/v1/prices`
#### Example with a successful response:
- Request:
  - `date` is the date and time of the request.
  - `productId` is the ID of the product.
  - `brandId` is the ID of the brand.
```url
http://localhost:8080/api/v1/prices?date=2020-06-14T10:00:00&productId=35455&brandId=1
```
- Response:
```json
[
  {
    "startDate": "2020-06-14 00:00:00",
    "endDate": "2020-12-31 23:59:59",
    "price": 35.5,
    "product_id": 35455,
    "brand_id": 1,
    "price_list": 1
  }
]
```
