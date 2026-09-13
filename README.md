# 🖥️ PCVault
### JavaFX + MySQL Desktop E-Commerce Application

A desktop e-commerce application for browsing and purchasing PC components (CPU, GPU, RAM, SSD). Built with JavaFX for the UI and MySQL for data persistence, featuring full authentication, a shopping cart, inventory management, and a light/dark theme toggle.

---

## 🛠️ Technology Stack

| Technology | Details |
|---|---|
| Java | JDK 11+ |
| JavaFX | Desktop UI framework |
| MySQL | localhost:3306 |
| JDBC | MySQL Connector/J |
| IDE | NetBeans |
| CSS | Custom light-theme.css / dark-theme.css |

---

## 📁 Project Structure

```
Project/
└── src/
    └── Java_Project/
        ├── Project_1.java          # Main application — all pages in one file
        ├── light-theme.css         # Light mode stylesheet
        ├── dark-theme.css          # Dark mode stylesheet
        ├── 14.png                  # Login screen logo
        ├── cpu.png / gpu.png / ram.png / ssd.png
        └── [product images]
```

---

## 🗄️ Database Setup

**Database name:** `Project_JavaFX`  
**Connection:** `jdbc:mysql://localhost:3306/Project_JavaFX`  
**Credentials:** `root` / *(empty password)*

### Create Tables

```sql
CREATE DATABASE Project_JavaFX;
USE Project_JavaFX;

CREATE TABLE USERS (
    `full name` VARCHAR(100),
    email       VARCHAR(100) PRIMARY KEY,
    password    VARCHAR(50),
    telephone   VARCHAR(10),
    gender      VARCHAR(10)
);

CREATE TABLE ITEMS (
    name     VARCHAR(50) PRIMARY KEY,
    price    INT,
    quantity INT
);
```

### USERS Columns

| Column | Type | Notes |
|---|---|---|
| full name | VARCHAR | User's full name |
| email | VARCHAR | Primary key — used as login ID |
| password | VARCHAR | User password |
| telephone | VARCHAR(10) | Format: `07XXXXXXXX` |
| gender | VARCHAR | Male or Female |

### ITEMS Columns

| Column | Type | Notes |
|---|---|---|
| name | VARCHAR | No spaces — must match code exactly |
| price | INT | Price in USD |
| quantity | INT | Stock — decremented on purchase |

---

## ⚙️ Setup & Running

### Prerequisites
- Java JDK 11+
- JavaFX SDK on the build path
- MySQL Server running on `localhost:3306`
- MySQL Connector/J JAR on the classpath

### Steps
1. Clone the repo and open in NetBeans
2. Create the database and tables using the SQL above
3. Populate the `ITEMS` table using the product names in the Products section
4. Update the image and CSS file paths in `Project_1.java` to match your machine
5. Run `Project_1.java` as the main class

---

## ✨ Features

| Screen | Features |
|---|---|
| Login | MySQL auth, show/hide password, tooltips |
| Sign Up | Full validation (name, email, password, phone, gender), duplicate checking |
| Home | 4 category tiles (CPU, GPU, RAM, SSD) with hover effects |
| Products | ComboBox category filter, product cards with image / price / quantity |
| Cart | Item list, re-add / remove items, total cost from DB, purchase confirmation |
| Settings | View account info, change password, delete account, logout, user count |
| Theme | Light/Dark toggle applied globally across all scenes |

---

## 🛒 Products Reference

> The `name` column in `ITEMS` must match these exactly (no spaces).

### CPU
| DB Name | Display Name | Price |
|---|---|---|
| AMDRayzen9 | AMD Rayzen 9 | $450 |
| AMDRayzen7 | AMD Rayzen 7 | $300 |
| AMDRayzen5 | AMD Rayzen 5 | $200 |
| InteliCore7 | Intel iCore 7 | $320 |
| InteliCore5 | Intel iCore 5 | $180 |
| InteliCore3 | Intel iCore 3 | $110 |

### GPU
| DB Name | Display Name | Price |
|---|---|---|
| RTX5070 | RTX 5070 | $600 |
| RTX5060 | RTX 5060 | $400 |
| RX9070 | RX 9070 | $580 |
| RX9060 | RX 9060 | $350 |

### RAM
| DB Name | Display Name | Price |
|---|---|---|
| Samsung32Ram | Samsung 32 Ram | $120 |
| Samsung16Ram | Samsung 16 Ram | $70 |
| Samsung8Ram | Samsung 8 Ram | $40 |

### SSD
| DB Name | Display Name | Price |
|---|---|---|
| Crucial4TB | Crucial 4TB | $350 |
| Crucial2TB | Crucial 2TB | $180 |
| Crucial1TB | Crucial 1TB | $90 |

---

## 🔑 Password Rules

- Length: **6 to 18 characters**
- At least one **lowercase** letter (a–z)
- At least one **uppercase** letter (A–Z)
- At least one **digit** (0–9)
- At least one **special character**: `@` `-` `_` `$`

---

## 👤 Author

**Moath Refaie**  
Applied Science University — Faculty of Information Technology, Amman, Jordan  
GitHub: [github.com/Mre195](https://github.com/Mre195)
