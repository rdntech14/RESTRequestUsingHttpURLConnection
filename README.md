REST Request Using HttpURLConnection
=====

**This project demonstrates following :**

**1) how to send Get request and read response as String**

```
getRequestDemo();
```

**2) how to send POST request and JSON request body is in Java String. Convert String into bytes and send.**

```
postRequestDemo1();
```

**3) how to send POST request and JSON request body is in .json file. Read .json file and convert into String and String into bytes.**

```
postRequestDemo2();
```

**4) how to parse post response using jackson and response is in JSON object**

```
postRequestDemo3();
```

**5) how to parse get response using jackson and response is in JSON Array**

```
postRequestDemo4();
```


*Note : To parse response in Java, we may use following libraries*

*1. JSON.simple*

*2. GSON*

*3. Jackson*

*we are going to use Jackson - good for big json file*


# Test

To test this project place CreateRESTFulService.war from ProjectOutputWar/CreateRESTFulService.war to under tomcat webapps folder and start tomcat.

Try following request using Postman with Header value *Content-type : application/json*

*Note: this will not work over browser as this is only supporting JSON, and no query parameter is set to take input for different formats like plain text, xml etc*
	
	http://localhost:8080/CreateRESTFulService/rest/employee/list — to get all employees list
	
	http://localhost:8080/CreateRESTFulService/rest/employee/update/id — to update an employee info
	
***Examples :***

**GET**

*All employees*

URL : http://localhost:8080/CreateRESTFulService/rest/employee/list

Output :

```
[
    {
        "firstName": "Anne",
        "id": 1001,
        "lastName": "Brown"
    },
    {
        "firstName": "Ben",
        "id": 1002,
        "lastName": "Miller"
    },
    {
        "firstName": "Joe",
        "id": 1003,
        "lastName": "Johnson"
    },
    {
        "firstName": "Mike",
        "id": 1004,
        "lastName": "Smith"
    },
    {
        "firstName": "Tom",
        "id": 1005,
        "lastName": "Williams"
    }
]
```

**PUT**

**Update employee**

URL : http://localhost:8080/CreateRESTFulService/rest/employee/update/1003

Input 

```
{
    {
        "firstName": "Joe",
        "id": 1003,
        "lastName": "Lisa"
    }
}
```

Output :

```
{
    "message": "Employee Updated"
}
```
