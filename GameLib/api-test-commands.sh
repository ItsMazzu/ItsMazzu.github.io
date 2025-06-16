#!/bin/bash

# Test GET all consoles
curl -X GET http://localhost:8080/api/favorites/consoles

# Test POST a new console
curl -X POST http://localhost:8080/api/favorites/consoles -H "Content-Type: application/json" -d '{"name":"Test Console","description":"Test Description","releaseDate":"2023-01-01","evaluation":4.5}'

# Test DELETE a console
curl -X DELETE http://localhost:8080/api/favorites/consoles/Test%20Console

# Test GET all games
curl -X GET http://localhost:8080/api/favorites/games

# Test POST a new game
curl -X POST http://localhost:8080/api/favorites/games -H "Content-Type: application/json" -d '{"name":"Test Game","description":"Test Description","releaseDate":"2023-01-01","evaluation":4.0}'

# Test DELETE a game
curl -X DELETE http://localhost:8080/api/favorites/games/Test%20Game

# Test GET all reviews
curl -X GET http://localhost:8080/api/reviews

# Test POST a new review
curl -X POST http://localhost:8080/api/reviews -H "Content-Type: application/json" -d '{"reviewerName":"Tester","gameName":"Test Game","rating":4,"comment":"Great game!"}'
