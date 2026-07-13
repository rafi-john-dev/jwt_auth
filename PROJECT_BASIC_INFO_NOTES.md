\# Creating Additional Documentation Files in a GitHub Repository



GitHub automatically displays only one file named \*\*README.md\*\* on the repository's home page. However, you can create as many Markdown (`.md`) files as you want for organizing your project documentation.



Example project structure:



```

jwt-auth/

│

├── README.md                     ← Main project documentation

├── GIT\_NOTES.md                  ← Git commands and workflow

├── SPRING\_BOOT\_NOTES.md          ← Spring Boot concepts

├── SPRING\_SECURITY\_NOTES.md      ← Spring Security \& JWT notes

├── SWAGGER\_SETUP.md              ← Swagger integration guide

├── DEPLOYMENT\_GUIDE.md           ← Render deployment guide

├── POSTGRESQL\_NOTES.md           ← PostgreSQL notes

├── INTERVIEW\_QUESTIONS.md        ← Interview preparation

├── pom.xml

├── src/

└── ...

```



\---



\# Step 1: Create a New Markdown File



Open your project in IntelliJ IDEA.



Right Click on your project



↓



\*\*New\*\*



↓



\*\*File\*\*



↓



Enter the file name.



Example:



```

GIT\_NOTES.md

```



Click \*\*OK\*\*.



Repeat the same process for other documentation files.



Example:



```

SWAGGER\_SETUP.md



DEPLOYMENT\_GUIDE.md



SPRING\_BOOT\_NOTES.md



SPRING\_SECURITY\_NOTES.md



POSTGRESQL\_NOTES.md



INTERVIEW\_QUESTIONS.md

```



> \*\*Important:\*\* These files should be created in the project root (same level as `README.md` and `pom.xml`), \*\*not inside the `src` folder\*\*.



Correct structure:



```

jwt-auth/

│

├── README.md

├── GIT\_NOTES.md

├── SWAGGER\_SETUP.md

├── DEPLOYMENT\_GUIDE.md

├── pom.xml

└── src/

```



\---



\# Step 2: Add Content



Open the newly created file.



Example:



```

GIT\_NOTES.md

```



Paste your Git notes into it.



Save the file.



Repeat this process for every documentation file.



\---



\# Step 3: Link Documentation from README.md



Open your existing \*\*README.md\*\*.



Scroll to the bottom (or wherever you want the documentation section).



Add the following:



```markdown

\---



\# Documentation



This repository also contains additional documentation.



\- \[Git Notes](GIT\_NOTES.md)

\- \[Spring Boot Notes](SPRING\_BOOT\_NOTES.md)

\- \[Spring Security Notes](SPRING\_SECURITY\_NOTES.md)

\- \[Swagger Setup](SWAGGER\_SETUP.md)

\- \[Deployment Guide](DEPLOYMENT\_GUIDE.md)

\- \[PostgreSQL Notes](POSTGRESQL\_NOTES.md)

\- \[Interview Questions](INTERVIEW\_QUESTIONS.md)

```



How Markdown links work:



```

\[Text Visible to User](FileName.md)

```



Example:



```

\[Git Notes](GIT\_NOTES.md)

```



Here,



```

Git Notes

```



is the clickable text.



```

GIT\_NOTES.md

```



is the file that opens when clicked.



GitHub automatically understands that the file exists in the same repository.



\---



\# Step 4: Save Changes



Save all Markdown files.



Your project now looks like:



```

jwt-auth/

│

├── README.md

├── GIT\_NOTES.md

├── SPRING\_BOOT\_NOTES.md

├── SPRING\_SECURITY\_NOTES.md

├── SWAGGER\_SETUP.md

├── DEPLOYMENT\_GUIDE.md

├── POSTGRESQL\_NOTES.md

├── INTERVIEW\_QUESTIONS.md

├── pom.xml

└── src/

```



\---



\# Step 5: Push Changes to GitHub



Open Terminal.



Execute:



```bash

git status

```



Purpose:



Shows all newly created Markdown files.



Example output:



```

Untracked files:



README.md

GIT\_NOTES.md

SPRING\_BOOT\_NOTES.md

SWAGGER\_SETUP.md

DEPLOYMENT\_GUIDE.md

```



Stage all files:



```bash

git add .

```



Create a commit:



```bash

git commit -m "Add project documentation"

```



Push changes:



```bash

git push

```



\---



\# Step 6: Verify on GitHub



Open your GitHub repository.



GitHub automatically displays your \*\*README.md\*\*.



Scroll down.



You'll now see:



```

Documentation



• Git Notes



• Spring Boot Notes



• Spring Security Notes



• Swagger Setup



• Deployment Guide



• PostgreSQL Notes



• Interview Questions

```



Each item is clickable.



For example,



Click:



```

Git Notes

```



GitHub opens:



```

GIT\_NOTES.md

```



Click:



```

Swagger Setup

```



GitHub opens:



```

SWAGGER\_SETUP.md

```



No additional configuration is required.



\---



\# Example Repository View



```

JWT Authentication System



Features



Installation



Deployment



Documentation



• Git Notes



• Spring Boot Notes



• Spring Security Notes



• Swagger Setup



• Deployment Guide



• PostgreSQL Notes



• Interview Questions

```



Each link opens the corresponding Markdown file directly within the repository.



\---



\# Recommended Documentation Structure



```

jwt-auth/

│

├── README.md                     ⭐ Main project overview

│

├── GIT\_NOTES.md                  ⭐ Git commands and workflows

│

├── SPRING\_BOOT\_NOTES.md          ⭐ Spring Boot concepts

│

├── SPRING\_SECURITY\_NOTES.md      ⭐ JWT and Spring Security

│

├── SWAGGER\_SETUP.md              ⭐ Swagger/OpenAPI setup

│

├── DEPLOYMENT\_GUIDE.md           ⭐ Render deployment steps

│

├── POSTGRESQL\_NOTES.md           ⭐ PostgreSQL configuration

│

├── INTERVIEW\_QUESTIONS.md        ⭐ Common interview questions

│

├── pom.xml

└── src/

```



\---



\# Benefits of Organizing Documentation This Way



✅ Keeps `README.md` clean and focused on the project overview.



✅ Separates detailed notes into dedicated documents.



✅ Makes the repository easier to navigate for recruiters, interviewers, and collaborators.



✅ Allows you to build a personal knowledge base while working on projects.



✅ GitHub automatically renders Markdown files beautifully, making them easy to read online.

