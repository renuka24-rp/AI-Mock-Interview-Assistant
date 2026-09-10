# AI Mock Interview Assistant
## About the Project

AI Mock Interview Assistant is a web application made for students and job seekers who want to practice interviews. The application uses AI to create interview questions and evaluate the user's answers. It also provides a Resume Analyzer that gives suggestions to improve a resume.
The main purpose of this project is to give users a simple platform where they can practice interviews without needing a real interviewer. After completing an interview, the user can get AI-generated feedback and understand where they can improve.

## Technologies Used

* **Frontend:** React
* **Backend:** Spring Boot
* **Database:** H2 Database
* **Security:** Spring Security and JWT


## Application Workflow 

When a new user opens the application, they first need to register an account. The registration details are sent from React to the Spring Boot backend and the user information is stored in the H2 database.

 After registration, the user can log in using their email and password. Spring Security checks the login details. If the details are correct, a JWT token is generated and sent to the frontend. This token is then used for accessing the protected features of the application.
 
 After login, the user can start an AI mock interview. The application sends a request to the backend, and the backend communicates with Gemini AI to generate interview questions. The question is shown to the user, and the user provides an answer.
 The answer is then sent to the Spring Boot backend. The backend sends the answer to Gemini AI for evaluation. Gemini AI analyzes the answer and provides feedback. This feedback is then displayed to the user so they can understand their strengths and areas for improvement.
 
 The user can also use the Resume Analyzer. The resume is sent to the backend and the required information is provided to Gemini AI. The AI analyzes the resume and gives suggestions related to skills, content, and areas that can be improved.
 
 ## Project Structure
 
The project has two main parts: frontend and backend. The frontend contains the React application, which handles the user interface. The backend contains the Spring Boot application, which handles APIs, authentication, database operations, and AI communication.
 
<img width="565" height="298" alt="image" src="https://github.com/user-attachments/assets/13c5613d-23af-4078-ba8a-2c230fdecbe3" />

## What I Learned

 While working on this project, I learned how a React frontend can communicate with a Spring Boot backend using APIs. I also learned the basics of database connectivity, user authentication using Spring Security and JWT, and how to integrate an AI service into a web application.
 
 This project also helped me understand how different technologies like frontend, backend, database, security, and AI can work together to build a complete full-stack application.
