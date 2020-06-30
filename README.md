# SpringForQA
for full core review of the capstone project


##deployment steps
###prerequisites 

    * Tomcat server 9.0.31 or higher
    * docker-compose is available
    * MySQl 5.0 or higher is available
    * Java 1.8

1 - after downloading/cloning the repo - perform docker-compose up in order to enable DB for the app

2 - In IDEA Edit configuration in the following way : Use Tomcat Server 9.0 , local deployment .
Specify JRE as 1.8 . On the deployment tab specify - artefact -> war_exploded

3 - When done -> Run Tomcat

4 - follow the GUI hints to make app work or ALternatively you may use Postman or curl in the terminal to ensure app behaviour is consistent with the requirements.
