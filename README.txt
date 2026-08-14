================================================================================
              E-COMMERCE API AUTOMATION FRAMEWORK
================================================================================

Project       : E-Commerce Order & Customer Management API Automation Framework
Domain        : E-Commerce / Retail
Language      : Java 21
Automation    : Rest Assured
Test Framework: TestNG
Build Tool    : Maven
Version Control: Git / GitHub

GitHub Repository:
https://github.com/Samiksha-97/ecommerce-api-automation


================================================================================
1. PROJECT OVERVIEW
================================================================================

This project is a Java-based REST API automation framework developed using
Rest Assured, TestNG, Maven, and Jackson.

The framework is designed to automate and validate REST APIs for an
E-Commerce application.

The main objective of this project is to provide a reusable, maintainable,
and scalable API automation framework that can be used for functional,
positive, negative, CRUD, business validation, workflow, and regression
testing.

The framework validates:

- HTTP status codes
- Response body
- JSON fields
- JSONPath expressions
- Response content type
- Request payloads
- Request headers
- Dynamic response data
- Positive scenarios
- Negative scenarios
- CRUD operations
- Business validations
- API workflows
- Regression scenarios


================================================================================
2. BUSINESS PROBLEM
================================================================================

In an E-Commerce application, APIs are responsible for important operations
such as:

- User authentication
- Customer/user management
- Product management
- Order/cart management
- Order creation
- Order updates
- Order retrieval
- Negative and invalid request handling

Manually validating these APIs for every release is time-consuming and can
result in defects being missed.

This automation framework reduces manual validation effort by automatically
executing API regression tests and validating the expected behavior of the
application.


================================================================================
3. PROJECT OBJECTIVES
================================================================================

The major objectives of this framework are:

1. Automate REST API functional testing.
2. Validate positive and negative API scenarios.
3. Automate CRUD operations.
4. Validate request and response data.
5. Validate HTTP status codes and response content.
6. Create reusable API classes.
7. Centralize common Rest Assured configuration.
8. Externalize environment configuration.
9. Use POJO/model classes for request payloads.
10. Organize tests using TestNG.
11. Execute the complete regression suite using Maven.
12. Maintain the project using Git and GitHub.
13. Provide a framework structure that can be extended for CI/CD.


================================================================================
4. TECHNOLOGY STACK
================================================================================

Technology              Purpose
--------------------------------------------------------------------------------
Java 21                  Programming language
Rest Assured 5.5.6      REST API automation
TestNG 7.11.0           Test execution and assertions
Maven                   Build and dependency management
Jackson 2.19.2          JSON serialization/deserialization
Hamcrest                Response assertions
Git                     Version control
GitHub                  Source-code repository
Eclipse                 Development environment
Postman                 API exploration/manual validation


================================================================================
5. APPLICATION API MODULES
================================================================================

The framework is organized into the following major API modules:

1. Authentication
2. Product Management
3. User Management
4. Order/Cart Management
5. Negative Testing
6. Workflow Testing
7. Payment-related testing structure


================================================================================
6. AUTHENTICATION API
================================================================================

Authentication functionality is implemented through AuthApi.java.

The authentication module is responsible for handling authentication-related
API operations and validating authentication responses.

The framework structure allows authentication functionality to be reused by
other API modules when required.


================================================================================
7. PRODUCT API AUTOMATION
================================================================================

The Product API module automates CRUD operations for products.

API operations include:

GET    /products
GET    /products/{id}
POST   /products
PUT    /products/{id}
DELETE /products/{id}


Product scenarios include:

- Retrieve all products
- Retrieve product by ID
- Create a product
- Update a product
- Delete a product
- Validate product ID
- Validate product title
- Validate product price
- Validate product category
- Validate product rating
- Validate product rating count
- Validate invalid product scenarios


Product-related classes include:

ProductApi.java
ProductAPITest.java
ProductRequest.java
ProductTestData.java


================================================================================
8. USER API AUTOMATION
================================================================================

The User API module automates complete CRUD operations.

API operations include:

GET    /users
GET    /users/{id}
POST   /users
PUT    /users/{id}
DELETE /users/{id}


User scenarios include:

- Retrieve all users
- Retrieve user by ID
- Create user
- Update user
- Delete user
- Validate user ID
- Validate username
- Validate email
- Validate password
- Validate response content type
- Validate HTTP status codes


User-related classes include:

UserApi.java
UserApiTest.java
UserRequest.java
UserTestData.java


================================================================================
9. ORDER / CART API AUTOMATION
================================================================================

Order management is one of the major business modules of this framework.

The framework automates order/cart-related operations.

API operations include:

GET    /carts
GET    /carts/{id}
POST   /carts/add
PUT    /carts/{id}


Order scenarios include:

- Retrieve all orders/carts
- Retrieve order by ID
- Create order
- Update order
- Verify created order
- Validate order response
- Validate invalid order ID
- Validate invalid user ID
- Validate invalid quantity
- Validate business rules


Order-related classes include:

OrderApi.java
OrderApiTest.java
OrderNegativeTest.java
OrderRequest.java
OrderProduct.java
OrderTestData.java
OrderBusinessValidator.java


================================================================================
10. NEGATIVE TESTING
================================================================================

Negative testing is implemented to validate how the API/framework behaves when
invalid data or invalid business conditions are provided.

Examples include:

- Invalid order ID
- Invalid user ID
- Invalid quantity
- Invalid request scenarios
- Business validation failures


Example business rule:

Product quantity must be greater than zero.


The framework also recognizes limitations of mock APIs where an API may accept
data that would normally be rejected by a real production application.

In such cases, business validation is handled separately using reusable
business validation utilities.


================================================================================
11. API WORKFLOW TESTING
================================================================================

The framework supports workflow-oriented API testing.

A business workflow can contain multiple dependent API operations.

Example:

Authenticate
     |
     v
Retrieve Product
     |
     v
Create Order
     |
     v
Verify Order
     |
     v
Update Order


Workflow testing validates that related API operations work together instead
of validating every endpoint only as an isolated operation.


================================================================================
12. API RESPONSE VALIDATION
================================================================================

The framework performs multiple levels of response validation.

------------------------------
GET REQUEST VALIDATIONS
------------------------------

GET APIs validate:

- HTTP status code
- Response body
- Response content type
- Response time
- JSON fields
- JSONPath values
- Mandatory fields
- Collection size where applicable


Example:

response.then()
        .statusCode(200)
        .contentType("application/json; charset=utf-8")
        .body("id", notNullValue());


------------------------------
POST REQUEST VALIDATIONS
------------------------------

POST APIs validate:

- Request body
- Request headers
- JSON payload
- HTTP status code
- Response body
- Response content type
- Dynamic response data
- Created resource information


Example:

response.then()
        .statusCode(201)
        .body("id", notNullValue())
        .body("title", equalTo(expectedTitle));


------------------------------
PUT REQUEST VALIDATIONS
------------------------------

PUT APIs validate:

- Resource ID
- Updated request data
- HTTP status code
- Updated response fields
- Response content type


------------------------------
DELETE REQUEST VALIDATIONS
------------------------------

DELETE APIs validate:

- HTTP status code
- Successful deletion response


================================================================================
13. FRAMEWORK ARCHITECTURE
================================================================================

The framework follows a layered and reusable architecture.

High-level structure:

                    E-COMMERCE APIs
                           |
                           v
                    REST ASSURED
                           |
              +------------+------------+
              |            |            |
              v            v            v
           API Layer    Test Layer   Config Layer
              |            |            |
              +------------+------------+
                           |
                           v
                         TESTNG
                           |
                           v
                         MAVEN
                           |
                           v
                    REGRESSION SUITE
                           |
                           v
                       GIT / GITHUB


The API layer contains reusable API operations.

The test layer contains TestNG test cases and validations.

The configuration layer contains environment and request specification
configuration.

Maven is used to build and execute the complete test suite.


================================================================================
14. BASE API DESIGN
================================================================================

BaseApi.java provides common request functionality to all API classes.

Example:

protected RequestSpecification request() {
    return given()
            .spec(requestSpec);
}


API classes extend BaseApi so that common request configuration does not need
to be duplicated.

Architecture:

                    BaseApi
                       |
          +------------+------------+
          |            |            |
          v            v            v
       AuthApi     ProductApi    UserApi
                                    |
                                    v
                                OrderApi


Benefits:

- Reusable request specification
- Less duplicate code
- Cleaner API classes
- Easier maintenance
- Consistent API configuration


================================================================================
15. REQUEST SPECIFICATION
================================================================================

RequestSpecFactory.java is responsible for creating the default Rest Assured
RequestSpecification.

Default configuration includes:

- Accept: application/json
- Content-Type: application/json


Example:

RestAssured
        .given()
        .accept("application/json")
        .contentType("application/json");


This configuration is created once and reused by the framework.


================================================================================
16. BASE TEST
================================================================================

BaseTest.java provides common test setup.

It:

1. Loads the base URL.
2. Creates the default RequestSpecification.
3. Makes the RequestSpecification available to test classes.


The test classes extend BaseTest.

Example structure:

BaseTest
   |
   +-- ProductAPITest
   |
   +-- UserApiTest
   |
   +-- OrderApiTest
   |
   +-- Authentication Tests
   |
   +-- Negative Tests
   |
   +-- Workflow Tests


================================================================================
17. CONFIGURATION MANAGEMENT
================================================================================

The framework uses external configuration instead of hardcoding the base URL
inside individual API classes.

Configuration flow:

config.properties
       |
       v
ConfigManager.java
       |
       v
Environment.java
       |
       v
BaseTest.java
       |
       v
RequestSpecification


ConfigManager.java loads values from config.properties.

Environment.java exposes the required environment configuration.

This approach makes it easier to maintain and extend the framework for
different environments in the future.


================================================================================
18. POJO / MODEL CLASSES
================================================================================

The framework uses Java model classes to represent API request payloads.

Examples:

ProductRequest
UserRequest
OrderRequest
OrderProduct


Benefits:

- Cleaner request construction
- Strongly typed request data
- Better maintainability
- Easier serialization/deserialization
- Avoids large hardcoded JSON strings in test classes


Example:

ProductRequest productRequest = new ProductRequest();

productRequest.setTitle("Automation Testing Product");
productRequest.setPrice(599.99);
productRequest.setDescription("Product created using API automation");


The object is then passed to the API layer.


================================================================================
19. ENDPOINT MANAGEMENT
================================================================================

All API endpoint paths are maintained centrally in:

Endpoints.java


Examples:

PRODUCTS       = "/products"
PRODUCT_BY_ID  = "/products/{id}"

USERS          = "/users"
USER_BY_ID     = "/users/{id}"

CARTS          = "/carts"
CART_BY_ID     = "/carts/{id}"


Benefits:

- No hardcoded endpoints in test methods
- Centralized endpoint maintenance
- Reduced duplication
- Cleaner API classes
- Easier endpoint changes


================================================================================
20. TEST DATA MANAGEMENT
================================================================================

Test data is maintained separately from test implementation.

Examples:

ProductTestData.java
UserTestData.java
OrderTestData.java


This approach keeps test classes focused on test logic and validation rather
than storing all test data directly inside the test methods.


================================================================================
21. TESTNG TEST SUITE
================================================================================

The project uses TestNG for test execution.

The central suite is:

testng.xml


The suite organizes different API test packages, including:

- Authentication Tests
- Product Tests
- User Tests
- Order Tests
- Negative Tests
- Payment Tests
- Workflow Tests
- Smoke Tests


This provides a centralized way to execute the regression suite.


================================================================================
22. MAVEN EXECUTION
================================================================================

The complete test suite can be executed using Maven.

Open Command Prompt in the project root directory:

Z:\java selenium projects\ecommerce-api-automation


Run:

mvn clean test


Maven will:

1. Clean previous build output.
2. Compile the project.
3. Execute the configured TestNG suite.
4. Run all automated test cases.
5. Generate the test execution result.


================================================================================
23. CURRENT TEST EXECUTION RESULT
================================================================================

The current automation framework contains 24 automated test cases.

Latest successful Maven execution:

Tests run : 24
Failures  : 0
Errors    : 0
Skipped   : 0

BUILD SUCCESS


This confirms that the complete TestNG regression suite executes successfully
through Maven.


================================================================================
24. GIT VERSION CONTROL
================================================================================

Git is used for source-code version control.

The project has been initialized as a Git repository and uses the main branch.

GitHub Repository:

https://github.com/Samiksha-97/ecommerce-api-automation


The project contains a .gitignore file to exclude generated and IDE-specific
files.

Ignored files/folders include:

target/
test-output/
.classpath
.project
.settings/
.idea/
*.iml


This keeps generated build files and IDE metadata out of the Git repository.


================================================================================
25. HOW TO CLONE THE PROJECT
================================================================================

Clone the repository using:

git clone https://github.com/Samiksha-97/ecommerce-api-automation.git


Navigate to the project:

cd ecommerce-api-automation


================================================================================
26. HOW TO RUN THE PROJECT
================================================================================

Prerequisites:

- Java 21
- Maven
- Git
- Eclipse or another Java IDE


Verify Java:

java -version


Verify Maven:

mvn -version


Verify Git:

git --version


Run the complete regression suite:

mvn clean test


Expected result:

Tests run: 24
Failures: 0
Errors: 0
Skipped: 0

BUILD SUCCESS


================================================================================
27. CURRENT FRAMEWORK FEATURES
================================================================================

The framework currently demonstrates:

- REST API automation
- Rest Assured
- Java
- TestNG
- Maven
- CRUD API automation
- Positive testing
- Negative testing
- Business-rule validation
- API workflow testing
- Request validation
- Response validation
- HTTP status validation
- JSONPath validation
- Hamcrest assertions
- Content-Type validation
- Request headers
- Dynamic response data validation
- POJO request models
- Serialization/deserialization
- Reusable API classes
- Base API layer
- Centralized endpoints
- Centralized test data
- Centralized request specification
- Environment configuration
- Configuration management
- Logging
- TestNG suite execution
- Maven regression execution
- Git version control
- GitHub repository


================================================================================
28. FUTURE ENHANCEMENTS
================================================================================

The framework can be further enhanced with:

1. Jenkins CI/CD pipeline
2. Automated GitHub-to-Jenkins execution
3. Advanced HTML reporting
4. Allure or Extent Reports
5. Data-driven testing
6. Multiple environment support
7. Environment-specific configuration
8. Authentication token management
9. Advanced API chaining
10. Test listeners
11. Retry mechanism
12. Parallel execution
13. Automated report publishing
14. Pipeline notifications


================================================================================
29. RESUME PROJECT DESCRIPTION
================================================================================

E-Commerce API Automation Framework

Developed a reusable REST API automation framework using Java, Rest Assured,
TestNG, and Maven to automate functional, positive, negative, CRUD, business
validation, and workflow scenarios for an e-commerce application. Implemented
reusable API layers, centralized request specifications, endpoint management,
externalized environment configuration, POJO-based request models,
JSONPath-based response validation, HTTP status and content-type validation,
logging, and Maven-based TestNG regression execution. Integrated the project
with Git and GitHub for source-code version control.


================================================================================
30. KEY INTERVIEW DISCUSSION POINTS
================================================================================

The following framework concepts can be discussed during technical interviews:

- Why Rest Assured is used for API automation
- Difference between API and UI automation
- RequestSpecification
- Response validation
- Status code validation
- JSONPath
- Hamcrest matchers
- POJO serialization/deserialization
- API abstraction
- BaseApi design
- RequestSpecFactory
- Configuration management
- Environment management
- Endpoint management
- Test data management
- Positive vs negative testing
- CRUD testing
- API workflow testing
- Business-rule validation
- TestNG annotations
- TestNG groups
- Maven lifecycle
- Git branching
- Git commits
- GitHub repository management
- CI/CD concepts
- Jenkins integration


================================================================================
31. PROJECT STATUS
================================================================================

Current implementation status:

API Automation Framework       : COMPLETED
Product API Automation        : COMPLETED
User API Automation           : COMPLETED
Order API Automation          : COMPLETED
Authentication Automation     : COMPLETED
Negative Testing              : COMPLETED
Workflow Testing              : COMPLETED
Base API Architecture         : COMPLETED
Request Specification         : COMPLETED
Configuration Management      : COMPLETED
TestNG Suite                  : COMPLETED
Maven Execution               : COMPLETED
Git Version Control            : COMPLETED
GitHub Repository             : COMPLETED

Current Regression Result:

24 / 24 TEST CASES PASSED


Jenkins CI/CD integration and advanced reporting are planned as future
enhancements.


================================================================================
32. AUTHOR
================================================================================

Name   : Samiksha
GitHub : https://github.com/Samiksha-97


================================================================================
                         END OF README
================================================================================