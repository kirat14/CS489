# Problem Statement: Restaurant Table Reservation System

In today's busy restaurant industry, managing table reservations efficiently is crucial to ensure a smooth and enjoyable dining experience for customers. Our goal is to develop a simple and user-friendly Restaurant Table Reservation System that allows restaurants to streamline the reservation process and optimize table utilization.

## Problem Description:

Restaurants often face challenges in efficiently managing table reservations, especially during peak hours or busy periods. Traditional reservation systems may be manual, leading to potential errors and inefficiencies. Therefore, there is a need for an automated system that simplifies the reservation process while providing flexibility to adjust table configurations based on changing needs.

### Features and Requirements:

**1. Table Management:**
   - The system should allow restaurant managers to add new tables easily, specifying table types (e.g., small, medium, large) and the initial number of chairs.
   - Restaurant staff should be able to modify existing tables, including changing the number of chairs associated with each table based on demand or space requirements.

**2. Reservation Management:**
   - Customers should be able to view available tables based on desired date, time, and table size.
   - The system should enable customers to make reservations online by selecting preferred dates, times, and providing the number of guests.
   - Restaurant staff should have a clear overview of reserved and available tables for efficient seating arrangements.

**3. Flexibility and Scalability:**
   - The system should be scalable to accommodate additional tables as the restaurant expands or changes its layout.
   - It should provide flexibility to adjust table configurations (e.g., change the number of chairs) easily without disrupting existing reservations.

**4. User Interface:**
   - The system should have an intuitive user interface for both customers making reservations and restaurant staff managing table assignments.
   - Customers should receive confirmation of their reservations via email or SMS.


# ER Diagram
![alt text](https://github.com/kirat14/CS489/blob/main/project/er-diagram.png?raw=true)




# 1. Sign Up

### 1.1 Admin
```
curl -X POST http://localhost:8080/api/auth/signup \
-H "Content-Type: application/json" \
-d '{"username":"admin","email":"admin@gmail.com","password":"123456","roles":["admin"]}'
```

### 1.2 Customer
```
curl -X POST http://localhost:8080/api/auth/signup \
-H "Content-Type: application/json" \
-d '{"username":"customer","email":"customer@gmail.com","password":"123456","roles":["customer"], "firstName": "john", "lastName": "Doe", "phoneNumber": "6412332400", "address": "1000 N 4th St"}'
```


## 2. Sign In
```
curl -X POST http://localhost:8080/api/auth/signin \
-H "Content-Type: application/json" \
-d '{"username":"customer","password":"123456"}'
```

```
curl -X GET http://localhost:8080/api/admin \
-H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcxNDE1NTc2NSwiZXhwIjoxNzE0MjQyMTY1fQ.op6hPRWMUEgsFJhpdv4R1iL7POk-dOU-Z7FD_SR47YfT_IUqznkjN0lb7uCngf8X-ngoYCvl3RqA6vHiwEEv_w"
```



## Add Table 
```
curl -X POST http://localhost:8080/admin/tables/add \
-H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcxNDE4OTg0MSwiZXhwIjoxNzE0Mjc2MjQxfQ.rcywNvHWNQE9VJU7SEydAkRg0v3VvPQvHkgTX_n2KjJtnjCRHx-vChEzSktmSG0quIfcocZs5HqinhLwQ2ogJA" \
-H "Content-Type: application/json" \
-d '{ "type": "MEDIUM", "chairs": 6 }'
```


### Get Table by ID (GET):
```
curl -X GET http://localhost:8080/admin/tables/1 \
-H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcxNDE2ODMzMywiZXhwIjoxNzE0MjU0NzMzfQ.ggFgd63aXsa8Hatk-yNYb1fMCNSMI8rR216Wcn8zOD2Kh1rxhGjYYyQ5DhQUlKB2HiVNVWpi9yIcm46fbknN7A"
```


### 3. Update Table (PUT):
```
curl -X PUT http://localhost:8080/admin/tables/1 \
-H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcxNDE2ODMzMywiZXhwIjoxNzE0MjU0NzMzfQ.ggFgd63aXsa8Hatk-yNYb1fMCNSMI8rR216Wcn8zOD2Kh1rxhGjYYyQ5DhQUlKB2HiVNVWpi9yIcm46fbknN7A" \
-H "Content-Type: application/json" \
-d '{ "type": "MEDIUM", "chairs": 6 }'
```


### 4. Delete table by ID (DELETE)

```
curl -X DELETE http://localhost:8080/admin/tables/1 \
-H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcxNDE2ODMzMywiZXhwIjoxNzE0MjU0NzMzfQ.ggFgd63aXsa8Hatk-yNYb1fMCNSMI8rR216Wcn8zOD2Kh1rxhGjYYyQ5DhQUlKB2HiVNVWpi9yIcm46fbknN7A"
```



### 1. Book a table

```
curl -X POST http://localhost:8080/booking/book \
-H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjdXN0b21lciIsImlhdCI6MTcxNDIxODI1MCwiZXhwIjoxNzE0MzA0NjUwfQ.TnJGJwaJF4iwNcvVyuQFO_ZqTE7R9La_WDYfya3bDC-TnBc2ZGR-_NILbgpUE4yRanN8jMRkC_2imFwCDMlIJA" \
-H "Content-Type: application/json" \
-d '{"restaurantTable": {"id": "2"}, "partySize": "4", "arrivalDateTime": "2024-04-27T09:00:00"}'
```

### 2. Delete a booking

```
curl -X DELETE http://localhost:8080/booking/5 \
-H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjdXN0b21lciIsImlhdCI6MTcxNDIxNzA2MywiZXhwIjoxNzE0MzAzNDYzfQ.YGf6yFhag2PqK5T5nM_POHPJTBs6evQXn-N2vpJdSXuRrOLchtqporeHpF8J1zMNJxbwnQBR-esObC379ofpjQ"
```

### 3. Cancel a booking

```
curl -X PUT http://localhost:8080/booking/cancel/2 \
-H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjdXN0b21lciIsImlhdCI6MTcxNDE5MTM2OCwiZXhwIjoxNzE0Mjc3NzY4fQ.cbeHBW2o9FzR8dUdoPbiDzlym4RDt-HC8q8tKFhHrjtUKo7xXAP5diFUn4D1Hrsnh1Y52am5eiptMYOcnNuUkw"
```

### 4. Get Customer booking history

```
curl -X GET http://localhost:8080/booking/customer \
-H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjdXN0b21lciIsImlhdCI6MTcxNDIxODI1MCwiZXhwIjoxNzE0MzA0NjUwfQ.TnJGJwaJF4iwNcvVyuQFO_ZqTE7R9La_WDYfya3bDC-TnBc2ZGR-_NILbgpUE4yRanN8jMRkC_2imFwCDMlIJA"
```





```
INSERT INTO roles(name) VALUES('ROLE_CUSTOMER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

INSERT INTO user_roles VALUES(1, 1);
```


