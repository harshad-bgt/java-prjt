# Placement Management System (PMS) - Console Edition

A beginner-style, enterprise-architecture college **Placement Management System** built with **Java 17**, **Spring Boot 3.2.3**, **Spring Data JPA**, **Hibernate ORM**, and **H2 / MySQL Database**. 

It features an interactive **Terminal Console Interface** (`CommandLineRunner`) for running and managing placement drives directly from your terminal!

---

## Table of Contents
1. [Project Overview](#project-overview)
2. [Console Interface Menu](#console-interface-menu)
3. [Key Modules & Business Logic](#key-modules--business-logic)
4. [Technology Stack](#technology-stack)
5. [Database Setup & Seed Data](#database-setup--seed-data)
6. [How to Run in Terminal](#how-to-run-in-terminal)
7. [Testing](#testing)

---

## Project Overview

The **Placement Management System (PMS)** automates campus placement drives directly in your terminal console. It features full backend entity relationships, business validation rules, custom exception handling, seed database initialization, and a menu-driven console runner.

---

## Console Interface Menu

When launched, the application displays an interactive terminal menu:

```text
==================================================
      PLACEMENT MANAGEMENT SYSTEM (CONSOLE)      
==================================================

--------------------------------------------------
MAIN MENU:
1. Student Management
2. Company Management
3. Job Opening Management
4. Job Application Management
5. Interview Management
6. Placement Management
7. View Dashboard Summary Statistics
0. Exit System
--------------------------------------------------
Select an option [0-7]: 
```

### Menu Options:
- **1. Student Management**: Add new students, list students, view student by ID, deactivate student profiles.
- **2. Company Management**: Register recruiting companies, view active companies, deactivate company profiles.
- **3. Job Opening Management**: Create new job drives, view active job openings with min CGPA and salary packages.
- **4. Job Application Management**: Submit candidate applications, validate CGPA & deadline eligibility, update application status (`SHORTLISTED`, `SELECTED`, `REJECTED`).
- **5. Interview Management**: Schedule multi-round interviews, view interview schedules, record interview results (`PASSED`, `FAILED`).
- **6. Placement Management**: Issue official placement offers to selected candidates, view placement ledger.
- **7. Dashboard Statistics**: View real-time statistics (Total Students, Active Companies, Open Jobs, Placed Students, Placement Rate %, Average & Highest Packages).

---

## Key Modules & Business Logic

- **Student & Company Profiles**: Manage student academic records and company recruitment details.
- **Job & Application Tracking**: Companies post jobs with criteria (like minimum CGPA). Students apply, and the system automatically checks eligibility based on rules.
- **Interview Scheduling**: Shortlisted applicants move to interview rounds, which are tracked and graded.
- **Placement Offers**: Successful candidates are granted placements, updating overall university statistics.
- **Exception Handling**: Features global exception handling with specific domain exceptions (`StudentNotFoundException`, `JobExpiredException`, `DuplicateApplicationException`).

---

## Technology Stack

- **Java**: Java 17
- **Framework**: Spring Boot 3.2.3, Spring Data JPA
- **Database**: H2 (In-memory for Dev/Test) / MySQL (Production)
- **Build Tool**: Maven (embedded wrapper available)
- **Mapping**: MapStruct (for Entity-DTO mapping)
- **Testing**: JUnit 5, Mockito

---

## Database Setup & Seed Data

By default, the application is configured to run with an in-memory **H2 Database** using `application-dev.yml`. 
Upon startup, the system is seeded with sample data from `data.sql` so you can immediately begin interacting with students, companies, and jobs.

To switch to MySQL:
1. Ensure your MySQL server is running.
2. Update the `spring.profiles.active` property to `mysql` in `application.yml`.
3. Configure the database credentials in `application-mysql.yml`.

---

## How to Run in Terminal

To run the interactive console application in your terminal (using the included Maven):

```bash
.\.maven\apache-maven-3.9.6\bin\mvn.cmd spring-boot:run
```

*(On Linux/macOS, use `./.maven/apache-maven-3.9.6/bin/mvn spring-boot:run`)*

---

## Testing

Run automated JUnit 5 tests to verify the core service logic:

```bash
.\.maven\apache-maven-3.9.6\bin\mvn.cmd clean test
```
