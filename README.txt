Import Project in Eclipse/STS

run Project 

once server is up and running 

(Using In-Memory DB (h2))
to access DB use below URL 
http://localhost:8080/h2-console

Enter Following details 
Driver class : org.h2.Driver
JDBC URL : jdbc:h2:mem:testdb
user name : sa
password: 

REST API 

1) Add Animals 

POST http://localhost:8080/api/animals 
request body sample 
{
	"name":"shabarish99",
	"type":"Fish",
	"move":"Swim"
}

2) GET animals based on Move

GET http://localhost:8080/api/animals/move/<value>
<value> can be Swim,Fly,Walk,Slither,Hop

3) Delete animals based on animal name which was used to create animals

DELETE http://localhost:8080/api/animals/<name>


