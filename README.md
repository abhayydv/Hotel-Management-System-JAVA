# Hotel Management System

A console-based **Hotel Management System** developed using **Java, JDBC, and MySQL**. This project helps manage hotel room reservations through basic CRUD operations such as adding, viewing, updating, searching, and deleting reservation records.

The main purpose of this project is to demonstrate database connectivity in Java using JDBC and perform real-time operations with a MySQL database.

---

## Features

* Reserve a hotel room
* View all reservations
* Search room number using reservation ID and guest name
* Update reservation details
* Delete reservation records
* MySQL database connectivity using JDBC
* Menu-driven console interface
* Simple and beginner-friendly project structure

---

## Technologies Used

* Java
* JDBC
* MySQL
* MySQL Connector/J
* Eclipse IDE / IntelliJ IDEA / VS Code

---

## Project Overview

This project allows hotel staff to manage customer reservations from the console. The user can insert new reservation details, view existing bookings, find a customer's room number, update reservation information, and delete records when required.

The application connects with a MySQL database using JDBC. All reservation records are stored permanently in the database, making the system more practical than normal file-based or temporary storage projects.

---

## Database Name

```sql
hotel_db_01
```

---

## Table Structure

Create a table named `reservations` inside the database:

```sql
CREATE TABLE reservations (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    guest_name VARCHAR(100) NOT NULL,
    room_number INT NOT NULL,
    contact_number VARCHAR(15) NOT NULL,
    reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

## Database Setup

Run the following SQL commands in MySQL:

```sql
CREATE DATABASE hotel_db_01;

USE hotel_db_01;

CREATE TABLE reservations (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    guest_name VARCHAR(100) NOT NULL,
    room_number INT NOT NULL,
    contact_number VARCHAR(15) NOT NULL,
    reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

## How to Run the Project

1. Clone this repository:

```bash
git clone https://github.com/your-username/hotel-management-system.git
```

2. Open the project in your Java IDE.

3. Add MySQL Connector/J JAR file to the project build path.

4. Create the database and table using the SQL commands given above.

5. Open the Java file and update your database username and password:

```java
private static final String url = "jdbc:mysql://127.0.0.1:3306/hotel_db_01";
private static final String username = "root";
private static final String password = "your_mysql_password";
```

6. Run the `HotelManagementSystem.java` file.

---

## Menu Options

When the program runs, the following options are shown:

```text
HOTEL MANAGEMENT SYSTEM

1. Reserve a room
2. View Reservations
3. Get Room Number
4. Update Reservations
5. Delete Reservations
0. Exit
```

---

## Project Functionalities

### 1. Reserve a Room

This option allows the user to add a new reservation by entering the guest name, room number, and contact number.

### 2. View Reservations

This option displays all reservation records stored in the MySQL database in a table format.

### 3. Get Room Number

This option helps find the room number of a guest using reservation ID and guest name.

### 4. Update Reservations

This option allows the user to update guest name, room number, and contact number for an existing reservation.

### 5. Delete Reservations

This option deletes a reservation record from the database using reservation ID.

### 0. Exit

This option safely exits the application.

---

## Sample Output

```text
HOTEL MANAGEMENT SYSTEM

1. Reserve a room
2. View Reservations
3. Get Room Number
4. Update Reservations
5. Delete Reservations
0. Exit

Enter your choice: 1
Enter guest name: Rahul
Enter room number: 101
Enter contact number: 9876543210

Reservation successful!
```

---

## Folder Structure

```text
Hotel-Management-System/
│
├── src/
│   └── com/
│       └── hotel/
│           └── management/
│               └── system/
│                   └── one/
│                       └── HotelManagementSystem.java
│
├── README.md
└── .gitignore
```

---

## Important Note

Do not upload your real MySQL password on GitHub. Before uploading the project, replace your actual password with a dummy value:

```java
private static final String password = "your_mysql_password";
```

---

## Future Improvements

* Add login system for admin
* Use PreparedStatement for better security
* Add room availability checking
* Add customer checkout feature
* Add payment management
* Add GUI using Java Swing or JavaFX
* Create a web-based version using Servlet, JSP, and MySQL

---

## Author

**Rahul Kumar**

---

## Conclusion

This Hotel Management System is a simple Java-based console project that demonstrates how to connect Java with MySQL using JDBC. It is useful for understanding CRUD operations, database connectivity, SQL queries, and menu-driven programming in Java.
