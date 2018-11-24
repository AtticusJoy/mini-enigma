# mini-enigma
Group project of Group 1 for the CMSC 495 Capstone Course - Fall 2018

Setting up Keycloak

1.	Go to localhost:8080
2.	Username: admin
Password: admin
3.	Upper left corner -> Add realm -> Name: demo -> Click Create
4.	Upper left corner -> Clients -> Create -> Client ID: frontend-app -> Root URL: localhost:3000 -> Save
5.	Upper left corner -> Roles -> Add Role -> Role Name: EMPLOYEE -> Save
6.	Repeat the steps for MANGER
7.	Left side -> Groups -> New -> Name: Employees -> Save -> Role Mappings -> Highlight EMPLOYEE -> Add selected
8.	Repeat the steps for Manager group
9.	Left side -> Users -> Add user -> Username: any name -> Save -> Credentials -> New Password: any password, Password Confirmation: same password -> Reset Password -> Change Password -> Role Mappings: highlight EMPLOYEE -> Add selected
10.	Go to localhost:3000 -> Log in with the user and credentials that you created
