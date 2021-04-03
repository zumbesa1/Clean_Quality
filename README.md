[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.io/from-referrer/) 

### DevOpsStarter

DevOps Prototype
* Understanding DevOps by doing it :-)

## Required Tools
* Git (https://git-scm.com/)
* Node and NPM (https://nodejs.org/)
* Java SDK 12 (https://www.oracle.com/java/technologies/javase-jdk12-doc-downloads.html)

## Installation
* Clone repository with Git
* Open a console/terminal window and change directory to frontend:
```
cd frontend
npm install
npm run ng build
```
Now all javascript files required for the frontend are built.

## Deployment
* Open a console/terminal window and change directory to backend:
```
cd backend
gradle run
```
* You may check the backend is running by opening http://localhost:4567/services/ping in a web browser
* You may check the frontend is running by opening http://localhost:4567/ in a web browser

## Options
Some options may be set by using a environment variable or Java system property before executing the commands above:
* PORT=xyz serves on a different port