# Run this API
In order for this API to work you need to bring up a MongoDB database.
The easiest way to do this is use docker.

This project also requires Java 10+ to compile

<code>
sudo docker run -p 27017:27017 --name test-mongo -d mongo
</code>

Now that MongoDB is up we run the main method or
run the jar file which is in the target folder.

Once the project is running open up a browser and use the following endpoints:

# Use the API
This will get the most common word in the xml file

<code>
GET http://localhost:8080/words/most-common
</code>

This will get the least common word in the xml file

<code>
GET http://localhost:8080/words/least-common
</code>

This will get the most common word in the xml file from the db

<code>
GET http://localhost:8080/words/most-common-db
</code>

This will get the least common word in the xml file from db

<code>
GET http://localhost:8080/words/least-common-db
</code>

#Technologies used
This is a Spring Boot microservice which uses
* Spring Web
* Maven 
* MongoDb (in-memory and non-in-memory)
* Lombok
* Junit 5