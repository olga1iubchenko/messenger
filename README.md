##Email Messenger app

You need install Lombok and Cucumber plugins installed

Current application could be run in two modes for reading attributes for email template:
- Read from console 
- Read from file

Console mode is run by default.
If you want to use file read mode you need to package your application and define two parameters: 
1. file.name (name of file)
2. file.emailTemplateText (email text with attributes)

Please set up values at runtime e.g :

java -jar jarName -DpropertyName=value

