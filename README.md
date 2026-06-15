# Layered Library Management System

A high-performance command-line Library Management System built using **Java**, **JDBC**, and a production-grade **PostgreSQL** database schema. This application strictly follows the enterprise **Layered Architecture** and **Data Access Object (DAO)** design patterns.

---

## 🏗️ Architectural Framework Overview

The codebase is completely decoupled into separate conceptual layers to guarantee scalability, clean code practices, and ease of testing:

* **`com.library.model` (Domain Layer):** Holds pure POJO (Plain Old Java Objects) data models representing structural real-world entities mapped directly to PostgreSQL database configurations.
* **`com.library.dao` (Data Access Layer):** Isolate storage engine logic frameworks completely away from administrative calculations using custom implementation rules (`impl`) via safe, automated `PreparedStatement` boundaries protecting against SQL injection threats.
* **`com.library.service` (Business Logic Layer):** Orchestrates core validations and validation filters (e.g., verifying asset stock availability thresholds prior to logging a transaction boundary).
* **`com.library.presentation` (User Interface Layer):** Direct interactive text menus managing loop processes and capturing system interaction values streams cleanly from terminal prompts.

---

## 🗄️ Core Database Schema

The database relies on synchronized key references to process transactional operations across three advanced structural tracking tables:

```sql
-- 1. Create Books Catalog Table
CREATE TABLE books (
    book_id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    isbn VARCHAR(50) UNIQUE NOT NULL,
    total_copies INT NOT NULL,
    available_copies INT NOT NULL
);

-- 2. Create Members Profile Table
CREATE TABLE members (
    member_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(15)
);

-- 3. Create Issued Transactions Track Table
CREATE TABLE issued_books (
    issue_id SERIAL PRIMARY KEY,
    book_id INT REFERENCES books(book_id) ON DELETE CASCADE,
    member_id INT REFERENCES members(member_id) ON DELETE CASCADE,
    issue_date DATE DEFAULT CURRENT_DATE,
    return_date DATE, 
    fine_amount NUMERIC(10, 2) DEFAULT 0.00
);