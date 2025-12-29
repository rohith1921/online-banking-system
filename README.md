# 🏦 Online Banking System

A **full-stack Java web application** that simulates real-world online banking operations with **secure user authentication, account management, transactions, and admin control**, built using **Java, Servlets, JSP, JPA (Hibernate), MySQL, HTML, CSS, and JavaScript**.

This project is designed with **clean architecture**, **database-driven UI**, and **industry-level banking workflows**, making it suitable for **interviews and production-style demos**.

---

## 🚀 Features

### 👤 User Module
- User Registration & Login (Session-based authentication)
- Secure Dashboard with:
  - Multiple account support
  - Live balance display
  - Recent transactions
- Create Bank Accounts  
  - Savings, Current, Fixed Deposit, RD
- Deposit & Withdraw money
- Fund Transfer between accounts
- Balance Enquiry
- Statements:
  - Mini Statement
  - Full Statement
  - Statement by Date Range
- Change Account PIN
- Secure Logout with session invalidation

---

### 🛠️ Admin Module
- Admin Login
- Admin Dashboard
- View all users
- View all bank accounts
- Approve pending accounts
- Close active accounts
- View all transactions
- Filter transactions by account & date
- Secure admin logout
- Session protection & cache control

---

## 🧱 Architecture Overview

The application follows a **layered architecture**:

┌─────────────────┐
│   Controller    │ ← Servlets
│    (Servlets)   │
└────────┬────────┘
         │
┌────────▼────────┐
│  Service Layer  │ ← Business Logic
└────────┬────────┘
         │
┌────────▼────────┐
│  DAO Layer      │ ← JPA / Hibernate
│  (Data Access)  │
└────────┬────────┘
         │
┌────────▼────────┐
│   Database      │ ← MySQL
│   (MySQL)       │
└─────────────────┘

### Key Design Principles
- Separation of concerns
- No dummy data (100% DB-driven UI)
- PRG (Post-Redirect-Get) pattern
- Session-based authorization
- Admin & User role segregation

---

## 🧰 Tech Stack

### Backend
- Java (Core + Advanced)
- Java Servlets (`javax.servlet`)
- JSP
- JPA (Hibernate)
- MySQL
- Apache Tomcat 9

### Frontend
- HTML5
- CSS3 (Custom Design System)
- JavaScript
- Responsive UI (Desktop & Mobile)

### Tools
- Eclipse IDE
- MySQL Workbench
- Git & GitHub

---

## 📁 Project Structure

Online_Banking_System/
│
├── src/main/java/
│   ├── com/bank/
│   │   ├── controller/
│   │   │   ├── user/
│   │   │   │   ├── LoginServlet.java
│   │   │   │   ├── RegisterServlet.java
│   │   │   │   ├── DashboardServlet.java
│   │   │   │   ├── AccountServlet.java
│   │   │   │   ├── TransactionServlet.java
│   │   │   │   └── StatementServlet.java
│   │   │   │
│   │   │   └── admin/
│   │   │       ├── AdminLoginServlet.java
│   │   │       ├── AdminDashboardServlet.java
│   │   │       ├── UserManagementServlet.java
│   │   │       ├── AccountManagementServlet.java
│   │   │       └── TransactionManagementServlet.java
│   │   │
│   │   ├── service/
│   │   │   ├── UserService.java
│   │   │   ├── AccountService.java
│   │   │   ├── TransactionService.java
│   │   │   └── AdminService.java
│   │   │
│   │   ├── service/impl/
│   │   │   ├── UserServiceImpl.java
│   │   │   ├── AccountServiceImpl.java
│   │   │   ├── TransactionServiceImpl.java
│   │   │   └── AdminServiceImpl.java
│   │   │
│   │   ├── dao/
│   │   │   ├── UserDAO.java
│   │   │   ├── AccountDAO.java
│   │   │   ├── TransactionDAO.java
│   │   │   └── GenericDAO.java
│   │   │
│   │   ├── model/
│   │   │   ├── User.java
│   │   │   ├── Account.java
│   │   │   ├── Transaction.java
│   │   │   ├── Admin.java
│   │   │   └── enums/
│   │   │       ├── AccountType.java
│   │   │       └── TransactionType.java
│   │   │
│   │   └── util/
│   │       ├── DatabaseUtil.java
│   │       ├── SessionUtil.java
│   │       └── ValidationUtil.java
│   │
│   └── resources/
│       └── META-INF/
│           └── persistence.xml
│
├── src/main/webapp/
│   ├── WEB-INF/
│   │   ├── web.xml
│   │   └── lib/ (dependencies)
│   │
│   ├── css/
│   │   ├── style.css
│   │   ├── dashboard.css
│   │   └── admin.css
│   │
│   ├── js/
│   │   ├── validation.js
│   │   ├── dashboard.js
│   │   └── admin.js
│   │
│   ├── admin/
│   │   ├── admin-login.jsp
│   │   ├── admin-dashboard.jsp
│   │   ├── manage-users.jsp
│   │   ├── manage-accounts.jsp
│   │   └── view-transactions.jsp
│   │
│   ├── user/
│   │   ├── login.jsp
│   │   ├── register.jsp
│   │   ├── dashboard.jsp
│   │   ├── create-account.jsp
│   │   ├── deposit.jsp
│   │   ├── withdraw.jsp
│   │   ├── transfer.jsp
│   │   ├── statements.jsp
│   │   └── change-pin.jsp
│   │
│   ├── partials/
│   │   ├── header.jsp
│   │   ├── footer.jsp
│   │   └── navigation.jsp
│   │
│   ├── index.jsp
│   └── error.jsp
│
├── src/main/resources/
│   └── sql/
│       └── database-schema.sql
│
├── pom.xml (Maven configuration)
├── README.md
└── .gitignore

---

## 🔐 Security Highlights

- Session-based authentication
- Role-based access control (User / Admin)
- Cache-control headers to prevent back-button access
- Server-side validation
- PIN and credential validation
- No sensitive data exposed in UI

---

## 🧪 How to Run the Project

1. Clone the repository  
2. Import into **Eclipse** (Dynamic Web Project / Maven Web Project)  
3. Configure **MySQL database**  
4. Update `persistence.xml` with database credentials  
5. Deploy on **Apache Tomcat 9**  
6. Access the application:

http://localhost:8080/Online_Banking_System

---

## 🧠 Key Learning Outcomes

- End-to-end Java web application development
- Real-world banking workflows
- Servlet & JSP integration
- Hibernate / JPA usage
- Session & security management
- Admin-controlled system design
- Clean UI + backend integration

---

## 📌 Future Enhancements

- OTP-based authentication
- PDF / CSV statement download
- Email notifications
- Spring Boot migration
- REST API layer
- Dashboard analytics & charts

---

## 👨‍💻 Author

**Rohith Kumar Paswan**  
Java Developer | Full-Stack Developer  

📧 Email: rohithkumarpaswan1921@gmail.com  

---

## ⭐ Final Note

This project demonstrates **strong backend fundamentals, clean architecture, and professional UI integration**, making it ideal for **technical interviews, portfolio showcases, and real-world learning**.
