# How to start

For ease of use of the application Swagger 2.
When the application is started (public static void main method from DataHubAppApplication.class)
please copy and paste the URL: http://localhost:8080/swagger-ui.html into web browser.

In order to authenticate for particular role and allow respective functionality there were 2 roles predefined:
- For Customer:
    - username: customer
    - password: admin
- For Content Manager:
    - username: manager
    - password: user

#Database

H2 database is available for all roles. 
H2 database is available under the URL: http://localhost:8080/h2/
Data source access is configured with application.properties file.
To have an access to H2 console you need to pass:
- JDBC URL: jdbc:h2:mem:data_hub_app
- User Name: admin
- Password: admin