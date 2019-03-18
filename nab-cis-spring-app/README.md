Customer Information System

Introduction:

This application exposes below RESTful APIs for create/update/delete and get customers.
1. /POST - create customer - http://localhost:8080/cis/api/customers
2. /PUT - update existing customer - http://localhost:8080/cis/api/customers - RequestBody
3. /DELETE - delete customer - http://localhost:8080/cis/api/customers/1
4. /GET - retrieve customers - http://localhost:8080/cis/api/customers

Tools used:
1. Spring Boot
2. Hibernate
3. Spring Boot Data
4. Spring Boot Security
5. H2 In-memory database
6. MapStruct - Mapper API to convert entity to DTO and DTO to entity object.
7. Mockito, Junit5 - For Service Unit Testing and Integration testing
