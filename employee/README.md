# Simple application to Register employees and get/list employees  
Pre-requisites:
1. Java 1.8
2. Maven
3. Mongo DB

Run instructions
1. Download the code(git clone)
2. cd to the download directory 
3. mvn clean install
4. Run mongo instance : "<path\to\mongo>\mongod.exe" --dbpath="<path\to\data>\mongodata\employee"
5. java -jar target\employee-0.0.1-SNAPSHOT.jar
6. To register employee use POST 
POST /employee
{
  "firstName" : "firstName",
  "lastName" : "lastName",
  "gender" : "male",
  "dateOfBirth" : "01/01",
  "department" : "Home"
}
7. To list employees use:  
GET /employee


Further improvements:
1. Pagenation
2. Security
3. Junits
4. Secure DB password
5. Make this a docker image with kubernetes(if required)