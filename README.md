# LibraryManagement
Library Management for B-Secur Coding Test

Hi Declan, 

I have created library management application with jwt authorization. The Application is running on port 3001. Also Im using embedded derby as database 

Endpoints :  (Postman collection json is attached)
To create jwt token , POST method with /authenticate endpoint is used .

Post Body :
{
	"userName" : "user"
	"password" : "user"
}
I have used default username and password. 

The below lib management endpoints can be used to authorized jwt token produced using /authenticate endpoint

(method : Post)
1. To add books :  /addbooks  

(Method : GET)
2. To retrieve all books : /books 
3. To retrieve all available books : /availablebooks 
4. To retrieve all checked out books : /checkedbooks 
5. To retrieve all due books : /duebooks 
6. To checkout by Id : /book/{id}

