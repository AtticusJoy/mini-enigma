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
2.  Click on the Administration Console link
3.	Username: CronosAdmin Password: cmsc495
4.	Upper left corner -> click on Master -> Add realm -> Name: Cronos -> Click Create
4a. Verify that you are on the correct Realm. The dropdown in the upper left corner should be selecting "Cronos"
5.	Upper left corner -> Clients -> Create -> Client ID: frontend-app -> Save
6.  Upper left corner -> Clients -> frontend-app -> valid Redirect URIs -> http://localhost:8080/* -> Click "+" -> http://localhost:3000/* -> Click "+" -> Scroll down to Web Origin -> Re-enter previous two routes WITHOUT "/*"
7.	Upper left corner -> Roles -> Add Role -> Role Name: EMPLOYEE -> Save
8.	Repeat the step 7 for MANAGER
9.	Left side -> Groups -> New -> Name: Employees -> Save -> Role Mappings tab -> Highlight EMPLOYEE -> Add selected
10.	Repeat the step 9 for Managers group
11.	Left side -> Users -> Add user -> Username: any name -> Save -> Credentials tab -> New Password: any password, Password Confirmation: same password -> Reset Password -> Change Password -> Groups tab: highlight proper group for the type of user being created (Employees or Managers) -> Click on Join
11a. You can view the created users by clicking on the left side Users and then click the "View all users" button
12.	Go to localhost:3000 -> Log in with the user and credentials that you created
13. On first login with new user -> reset password -> confirm password -> reset password button

In case you want to do a hard reset, you want to close and purge all Docker containers by following the next commands:
1. docker ps (To inspect current running containers)
2. docker stop container id or docker stop $(docker ps -a -q) to stop all containers
3. docker system prune -f -a
4. docker volume prune

If you have Java 10 installed and want to switch to Java 8: Run this command in the terminal  
$ export JAVA_HOME=`/usr/libexec/java_home -v 1.8`
