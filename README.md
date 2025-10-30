# 🧠 Shodh-a-Code Contest Platform

A full-stack, Dockerized coding contest platform that simulates live coding events with real-time submissions, judging, and leaderboards.

---

## 🚀 Features

- **Join Contest**: Enter a contest ID and username to participate.
- **Problem Solving UI**: View problems, write code in the in-browser editor (Monaco).
- **Live Judging**: Submitted code is executed inside a Docker container for isolation.
- **Leaderboard**: Automatically updates every 15 seconds.
- **Fully Dockerized**: One-command startup using `docker-compose`.

---

## 🏗️ Architecture

┌──────────────────────────┐
│ Frontend (Next) │
│ • /join, /contest pages │
│ • Code editor, polling │
└──────────────┬───────────┘
│ REST API (JSON)
┌──────────────┴────────────┐
│ Backend (Spring Boot) │
│ • REST APIs │
│ • H2/MySQL persistence │
│ • Docker orchestration │
└──────────────┬────────────┘
│ docker run
┌──────────────┴────────────┐
│ Judge Container (JDK) │
│ • Executes user code │
│ • Validates I/O │
└───────────────────────────┘


---

## 🧩 Technology Stack

| Layer | Technologies |
|-------|---------------|
| **Frontend** | Next.js 14, TailwindCSS, Axios, Monaco Editor |
| **Backend** | Spring Boot 3, JPA, H2/MySQL, Lombok |
| **Judge Engine** | Docker (OpenJDK 17) |
| **Container Orchestration** | Docker Compose |

---

## ⚙️ Setup Instructions

### 🧰 Prerequisites
- Docker & Docker Compose installed
- (Optional) Node.js 18+ and JDK 17 if you want to run locally outside containers

### ▶️ Run Everything
```bash
git clone https://github.com/<yourusername>/shodh-a-code
cd shodh-a-code
docker-compose up --build


Access:

Frontend: http://localhost:3000

Backend API: http://localhost:8080/api

H2 Console: http://localhost:8080/h2-console
 (JDBC URL: jdbc:h2:mem:shodhacode)

🧠 API Overview
Contest APIs
Method	Endpoint	Description
GET	/api/contests/{id}	Fetch contest details and problems
GET	/api/contests/{id}/leaderboard	Retrieve live leaderboard
Submission APIs
Method	Endpoint	Description
POST	/api/submissions	Submit code for evaluation
GET	/api/submissions/{id}	Fetch submission status/output
Example Request
POST /api/submissions
{
  "userId": 1,
  "problemId": 2,
  "language": "java",
  "code": "public class Main { public static void main(String[] args) { ... } }"
}

Example Response
{
  "submissionId": 15,
  "status": "Pending"
}

🧱 Database Seed (data.sql)

The backend auto-populates demo data when launched:

Users

ID	Username
1	alice
2	bob

Contest

ID	Title	Description
1	Spring Boot Coding Contest	Test your coding skills!

Problems

ID	Title	Input	Output
1	Sum Two Numbers	2 3	5
2	Multiply Numbers	4 5	20

You can start solving these right after startup — no setup required.

🧰 Design Decisions & Trade-offs
Component	Design Choice	Rationale
Backend	Spring Boot + JPA	Clean API, mature ecosystem
Frontend	Next.js + Tailwind	Rapid UI development, live updates
Judging	Java ProcessBuilder invoking docker run	Safe code isolation
Polling	Chosen over WebSockets	Simpler MVP, stateless behavior
Database	H2 in-memory	Fast, portable demo (switchable to MySQL)
Orchestration	Docker Compose	One-command setup for full system
🔒 Security & Resource Isolation

Each submission runs inside a disposable Docker container (judge-env), ensuring:

No direct host access

Memory limit: 256 MB (--memory=256m)

CPU limit: 1 core (--cpus=1)

Auto-removal (--rm) after execution

The backend mounts the user’s code into /app, runs it, compares output, and stores the result.

📈 Future Enhancements

Support multiple languages (Python, C++)

Real-time updates via WebSockets instead of polling

Distributed judging using Kafka or RabbitMQ

Authentication and contest management dashboard

Persistent database (MySQL/PostgreSQL) for production

🧑‍💻 Development Tips

Run backend manually (without Docker)

cd backend
mvn spring-boot:run


Run frontend manually

cd frontend
npm install
npm run dev


The backend will be available at http://localhost:8080
,
the frontend at http://localhost:3000
.

🧾 License

MIT License © 2025 Shodh AI

🏁 Quick Summary
Component	URL	Description
Frontend	http://localhost:3000
	Next.js contest UI
Backend API	http://localhost:8080/api
	Spring Boot REST endpoints
Database	H2 (in-memory)	Auto-loaded with demo contest
Judge Engine	Dockerized JDK runtime	Executes & validates submissions
✅ One Command to Start Everything
docker-compose up --build


Then open → http://localhost:3000
 to start coding.