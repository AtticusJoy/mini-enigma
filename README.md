# mini-enigma
Group project of Group 1 for the CMSC 495 Capstone Course - Fall 2018

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
4.	Upper left corner -> Clients -> Create -> Client ID: frontend-app -> Root URL: localhost:3000 -> Save
5.	Upper left corner -> Roles -> Add Role -> Role Name: EMPLOYEE -> Save
6.	Repeat the step 5 for MANAGER
7.	Left side -> Groups -> New -> Name: Employees -> Save -> Role Mappings -> Highlight EMPLOYEE -> Add selected
8.	Repeat the step 7 for Managers group
9.	Left side -> Users -> Add user -> Username: any name -> Save -> Credentials -> New Password: any password, Password Confirmation: same password -> Reset Password -> Change Password -> Role Mappings: highlight EMPLOYEE -> Add selected
10.	Go to localhost:3000 -> Log in with the user and credentials that you created
11. On first login with new user -> reset password -> confirm password -> reset password button

Known Complications with Mac  
Something unique must be happening with Mac and Chrome as a CORS error appears there but not when the user/client is using windows with google chrome

To circumvent this for now:
1. In Keycloak (localhost: 8080) go to the demo realm, click on client, then front-end, then scroll down to "valid URIs" and replace what was auto filled with "*"
2. Open up google chrome without web security using the command $ open -a Google\ Chrome --args --disable-web-security —user-data-dir

In case you want to do a hard reset, you want to close and purge all Docker containers by following the next commands:
1. docker ps (To inspect current running containers)
2. docker stop <container id>
3. docker system prune -f -a
4. docker volume prune
