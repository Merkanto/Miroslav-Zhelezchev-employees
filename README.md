
# 📊 Longest-Working Employee Pair Finder

This full-stack application identifies the **pair of employees who have worked together on common projects for the longest period of time**, based on a CSV file input. It is built with:

- ✅ Java 17 + Spring Boot (Backend)
- ✅ Angular + Angular Material (Frontend)

---

## 🎯 Task Overview

> Identify the pair of employees who have worked together on common projects for the longest period of time.

### ✅ Functional Requirements:
- Upload a CSV file containing employee project records
- Handle `NULL` as today’s date
- Support multiple date formats
- Display a table of all common projects with:
  - Employee 1 ID
  - Employee 2 ID
  - Project ID
  - Days worked together

### ⭐ Bonus Requirement:
- Multiple date formats supported in input (e.g. `yyyy-MM-dd`, `dd/MM/yyyy`, `MM/dd/yyyy`, etc.)

---

## 📁 CSV Input Format

```
EmpID,ProjectID,DateFrom,DateTo
143,12,2013-11-01,2014-01-05
218,10,2012-05-16,NULL
143,10,2009-01-01,2011-04-27
218,12,2013-12-01,2014-01-10
150,12,2013-11-15,2013-12-31
150,10,2012-05-20,2012-11-05
```

> ✅ A sample file `employees.csv` is included in the backend project under:
```
src/main/resources/employees.csv
```

To test the app:
1. Download the file from that location to your **local machine**
2. Start the application
3. Use the Angular UI to **upload this CSV from your local machine**

---

## 🖥️ How to Run the Application

### ✅ Prerequisites
- Java 17
- Node.js & npm (v18+ recommended)
- Angular CLI (`npm install -g @angular/cli`)
- IntelliJ Ultimate (optional but supported)

---

### 🚀 Step-by-Step Instructions

#### 1. Clone the Repository

```bash
git clone https://github.com/Merkanto/Miroslav-Zhelezchev-employees.git
```

---

#### 2. Run the Backend (Spring Boot)

```bash
cd employee-collaboration
./mvnw spring-boot:run
```

The backend will be available at:  
```
http://localhost:8080
```

---

#### 3. Run the Frontend (Angular)

```bash
cd employee-collab-ui
npm install
ng serve
```

The frontend will be available at:  
```
http://localhost:4200
```

---

#### 4. Upload the Sample CSV

- Go to the frontend in your browser: `http://localhost:4200`
- Click **Choose File**
- Select the downloaded `employees.csv` from your machine
- Click **Upload**
- 🎉 View the resulting employee collaboration table

---

## 📦 Project Structure

```
employee-collaboration/
├── src/
│   ├── main/java/com/sirma/employeecollaboration/ ← 💻 Spring backend (runs on : 8080)
│   └── main/resources/employees.csv   ← 📄 Sample CSV to test
├── employee-collab-ui/                ← 💻 Angular frontend (runs on :4200)
└── README.md
```

---

## ✅ Technologies Used

| Layer        | Stack                        |
|--------------|------------------------------|
| Frontend     | Angular 16, Angular Material |
| Backend      | Java 17, Spring Boot 3       |
| CSV Parsing  | Apache Commons CSV           |
| Communication| REST (JSON), Multipart       |

---
