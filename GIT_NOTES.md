\# Spring Boot + Git + GitHub + Render + Swagger Notes



This document is a complete step-by-step guide that explains:



1\. How to upload a local project to GitHub for the first time.

2\. How to push future code changes.

3\. How to integrate Swagger UI into a Spring Boot project.

4\. Why each command is used.

5\. Why other commands are not used.



\---



\# PART 1 : Upload Local Project to GitHub (First Time)



Suppose your project structure is



```

JWT\_AUTH\_PROJECT

│

├── src

├── pom.xml

├── README.md

└── .gitignore

```



Assume your project is fully completed locally.



\---



\## Step 1 : Open Terminal



Open Terminal / CMD / PowerShell inside your project folder.



Example



```

cd JWT\_AUTH\_PROJECT

```



Purpose



Moves terminal into your project folder.



Without this, Git cannot locate your project.



\---



\## Step 2 : Initialize Git Repository



Command



```bash

git init

```



Purpose



Creates a hidden folder



```

.git

```



inside your project.



The .git folder stores



\- commit history

\- branches

\- tags

\- remote repositories



Without this folder Git cannot track files.



Why use git init?



Because this project has never been tracked by Git.



Why not use git clone?



git clone is used only when downloading an existing GitHub repository.



\---



\## Step 3 : Check Repository Status



Command



```bash

git status

```



Purpose



Shows



\- modified files

\- deleted files

\- new files

\- staged files



Example



```

Untracked files:



README.md

src/

pom.xml

```



Why?



Before committing we should know what Git sees.



\---



\## Step 4 : Add Files to Staging Area



Command



```bash

git add .

```



Purpose



Moves all current project files into Git's staging area.



Think of staging as



```

Project Files

&#x20;       ↓

Staging Area

&#x20;       ↓

Commit

```



Why git add . ?



"." means



"Add every file."



Useful for first commit.



Alternative



```

git add README.md

```



Adds only README.



Alternative



```

git add src/

```



Adds only src folder.



Why not use these?



Because for the first upload we usually want everything.



\---



\## Step 5 : Verify Staged Files



Command



```bash

git status

```



Output



```

Changes to be committed:

```



Now Git confirms files are staged.



\---



\## Step 6 : Create First Commit



Command



```bash

git commit -m "Initial project commit"

```



Purpose



Creates a snapshot of your project.



Commit = Save Point



Like pressing Save in a game.



Why use -m ?



Allows writing commit message directly.



Alternative



```

git commit

```



This opens a text editor.



Usually unnecessary.



\---



\## Step 7 : Create Empty Repository on GitHub



Go to



```

https://github.com

```



Click



```

New Repository

```



Example



```

jwt-auth

```



Do NOT



\- initialize README

\- initialize .gitignore



Because project already exists locally.



After creation GitHub shows



```

git remote add origin

```



\---



\## Step 8 : Connect Local Project to GitHub



Command



```bash

git remote add origin https://github.com/USERNAME/REPOSITORY.git

```



Purpose



Connects local repository to GitHub.



Why "origin"?



Origin is simply the default nickname for GitHub.



Alternative



```

git remote add myrepo URL

```



Works.



But almost every developer uses



```

origin

```



\---



\## Step 9 : Verify Remote



Command



```bash

git remote -v

```



Purpose



Shows



```

origin

```



with fetch and push URLs.



Useful for debugging.



\---



\## Step 10 : Rename Branch



Command



```bash

git branch -M main

```



Purpose



Makes current branch



```

main

```



GitHub default branch is main.



Why -M ?



Force rename.



Alternative



```

git branch -m main

```



Fails if branch already exists.



\-M forces rename.



\---



\## Step 11 : Push to GitHub



Command



```bash

git push -u origin main

```



Purpose



Uploads code.



Breakdown



git push



↓



Upload



origin



↓



GitHub



main



↓



Branch



\-u



↓



Remember this remote permanently.



Next time you only type



```

git push

```



instead of



```

git push origin main

```



\---



Repository is now available online.



\----------------------------------------------------------



\# PART 2 : Push Future Code Changes



Suppose you modified



```

SecurityConfig.java

```



or



```

README.md

```



\---



\## Step 1



Check changes



```bash

git status

```



Purpose



See modified files.



\---



\## Step 2



Stage changes



```bash

git add .

```



or



```bash

git add SecurityConfig.java

```



Why?



Git commits only staged files.



\---



\## Step 3



Create commit



```bash

git commit -m "Allow Swagger endpoints"

```



Good commit messages



```

Fix JWT validation



Add Swagger configuration



Deploy fixes



Update README



Implement login endpoint

```



Bad commit messages



```

Update



Done



Changes



Final

```



Because nobody understands them later.



\---



\## Step 4



Push



```bash

git push

```



Why only git push?



Because



```

git push -u origin main

```



was already executed once.



Git already remembers destination.



\---



Useful Commands



Current branch



```bash

git branch

```



Commit history



```bash

git log

```



Compact history



```bash

git log --oneline

```



Show differences



```bash

git diff

```



Undo staged file



```bash

git restore --staged filename

```



Discard local changes



```bash

git restore filename

```



Delete Git tracking



```bash

git rm filename

```



\----------------------------------------------------------



\# PART 3 : Swagger Integration (Spring Boot)



Swagger generates interactive API documentation.



Without Swagger



Developer needs



Postman



and documentation.



With Swagger



Developer opens browser.



Tests API instantly.



\---



\## Step 1



Add Dependency



pom.xml



```xml

<dependency>

&#x20;   <groupId>org.springdoc</groupId>

&#x20;   <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>

&#x20;   <version>2.8.9</version>

</dependency>

```



Purpose



Downloads Swagger libraries.



Without dependency



Swagger cannot exist.



\---



\## Step 2



Reload Maven



IntelliJ



```

Load Maven Changes

```



or



```

mvn clean install

```



Purpose



Downloads dependency.



\---



\## Step 3



Run Application



Swagger automatically creates



```

/v3/api-docs

```



and



```

/swagger-ui/index.html

```



\---



\## Step 4



Security Configuration



Permit Swagger



```java

.requestMatchers(

"/swagger-ui/\*\*",

"/swagger-ui.html",

"/v3/api-docs/\*\*",

"/webjars/\*\*"

).permitAll()

```



Why?



Otherwise JWT blocks Swagger.



\---



\## Step 5



Restart Application



Swagger available



```

http://localhost:8080/swagger-ui/index.html

```



\---



\## Step 6



Deploy



After deployment



```

https://YOUR\_APP.onrender.com/swagger-ui/index.html

```



\---



\## Step 7



Testing



Click



```

Try it Out

```



↓



Enter JSON



↓



Execute



↓



View Response



No Postman required.



\----------------------------------------------------------



\# Typical Spring Boot Deployment Flow



Develop Code



↓



Run Locally



↓



Test APIs



↓



Swagger Verification



↓



git status



↓



git add .



↓



git commit



↓



git push



↓



GitHub Repository Updated



↓



Render Detects Push



↓



Automatic Deployment



↓



Application Starts



↓



Swagger Live



↓



Users Access APIs



\----------------------------------------------------------



\# Git Command Summary



Initialize Repository



```bash

git init

```



Check Status



```bash

git status

```



Stage Everything



```bash

git add .

```



Stage One File



```bash

git add filename

```



Commit



```bash

git commit -m "message"

```



Connect GitHub



```bash

git remote add origin URL

```



Verify Remote



```bash

git remote -v

```



Rename Branch



```bash

git branch -M main

```



First Push



```bash

git push -u origin main

```



Future Push



```bash

git push

```



View Commit History



```bash

git log

```



Compact History



```bash

git log --oneline

```



Current Branch



```bash

git branch

```



Compare Changes



```bash

git diff

```



Restore File



```bash

git restore filename

```



Unstage File



```bash

git restore --staged filename

```



\----------------------------------------------------------



\# Final Development Workflow



Write Code



↓



Run Application



↓



Fix Bugs



↓



Test using Swagger



↓



git status



↓



git add .



↓



git commit -m "Meaningful message"



↓



git push



↓



GitHub Updated



↓



Render Deploys Automatically



↓



Verify Live Application



↓



Done

