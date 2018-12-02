# mini-enigma
Group project of Group 2 for the CMSC 495 Capstone Course - Fall 2018

Required Libraries/Tools:
Maven, Java 1.8, Docker, Node

Setting up the Project:
1. Open up the terminal or command prompt  
2. Navigate to folder
3. Run "mvn clean install"
4. Run "docker-compose up"

Once Everything Is initialized in Docker, go to "Setting up Keycloak step"  

Setting up Keycloak

1.	Go to localhost:8080
2.	Username: admin Password: admin
3.	Upper left corner -> click on Master -> Add realm -> Name: demo -> Click Create
4.	Upper left corner -> Clients -> Create -> Client ID: frontend-app -> Save
5.  Upper left corner -> Clients -> frontend-app -> valid Redirect URIs -> http://localhost:8080/* -> Click "+" -> http://localhost:3000/* -> Click "+" -> Scroll down to Web Origin -> Re-enter previous two routes WITHOUT "/*"
6.	Upper left corner -> Roles -> Add Role -> Role Name: EMPLOYEE -> Save
7.	Repeat the step 5 for MANAGER
8.	Left side -> Groups -> New -> Name: Employees -> Save -> Role Mappings -> Highlight EMPLOYEE -> Add selected
9.	Repeat the step 7 for Managers group
10.	Left side -> Users -> Add user -> Username: any name -> Save -> Credentials -> New Password: any password, Password Confirmation: same password -> Reset Password -> Change Password -> Groups: highlight Employees -> Click on Join
11.	Go to localhost:3000 -> Log in with the user and credentials that you created
12. On first login with new user -> reset password -> confirm password -> reset password button

In case you want to do a hard reset, you want to close and purge all Docker containers by following the next commands:
1. docker ps (To inspect current running containers)
2. docker stop container id or docker stop $(docker ps -a -q) to stop all containers
3. docker system prune -f -a
4. docker volume prune

If you have Java 10 installed and want to switch to Java 8: Run this command in the terminal . 
$ export JAVA_HOME=`/usr/libexec/java_home -v 1.8`
