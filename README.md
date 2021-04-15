# Marvelous application

## How to run
1. Put you API keys .\testapp\src\main\resources\application.properties
2. mvn spring-boot:run or start com.app.Application.main

## Swagger
GET -> http://localhost:8080/swagger-ui.html

## Task 1 - Page with comic
### Description
Using the Marvel API [https://developer.marvel.com/docs], pick a random story
featuring your favorite character (perhaps The Hulk?). Generate an HTML page
with the following characteristics:
* The story's description
* A list of names and pictures of the characters that features in the story
* The Marvel attribution text
### Solution
GET -> http://localhost:8080/story
 
## Task 2 - Rest API
### Description
Generate a Rest API with the following features (Encapsulate the Marvel API return some data aggregate about):
* given a set of characters, get all stories where they all appear together
* given a set of characters, get the number of comics and events that they appear together
* a ~~Secured~~ api where users can vote on their favourite character
### Solution
GET -> http://localhost:8080/characters/Vision,Wasp/stories

GET -> http://localhost:8080/characters/Spider-Man,Hulk,Thor/comicsAndEvents

GET -> http://localhost:8080/characterVote (results) | GET http://localhost:8080/characterVote/1/ (vote)

### TODO
* Implement more unit test, cover edge cases
* Implement error handling and server unavailability
* Implement security aspects
* Unify and improve model DTO objects
* Move hashing method from MarvelHttpClient
* Resolve issue with MD5 hashing some timestamp produce hash starting with number which is unacceptable by MarvelAPI
* Provide description to endpoints so Swagger can use it
* Implement integration test with mocked MarvelAPI
* Implement security like XSS protection
