# Job Portal

A web application where recruiters can post jobs and manage applications, and job seekers can browse listings and apply — each with their own dashboard.

Built with Java Spring Boot, Spring Security, MySQL, and Thymeleaf.

---

## Screenshots

![Login](J_P%20ScreenShots/Screenshot%202026-06-28%20134602.png)
![Register](J_P%20ScreenShots/Screenshot%202026-06-28%20134651.png)
![Jobs](J_P%20ScreenShots/Screenshot%202026-06-28%20134721.png)
![Dashboard](J_P%20ScreenShots/Screenshot%202026-06-28%20134811.png)

---

## Tech Stack

- Java 21 + Spring Boot 4.0
- Spring Security (role-based auth)
- Spring Data JPA + MySQL
- Thymeleaf (server-side templates)
- Maven

---

## Features

- Recruiter: post jobs, view applicants, update application status
- Job Seeker: browse and search jobs, apply, track status
- Separate dashboards per role
- Secure login and registration

---

## Run Locally

1. Create MySQL database: `CREATE DATABASE job_portal_db;`
2. Copy `application.properties.example` to `application.properties` and fill in your credentials
3. Run via Eclipse: right-click `JobPortalApplication.java` -> Run As -> Spring Boot App
4. Open `http://localhost:8080`

---

## Author

Mayur Kadale
