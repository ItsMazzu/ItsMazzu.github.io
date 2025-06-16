# Test GET all consoles
Invoke-RestMethod -Uri http://localhost:8080/api/favorites/consoles -Method Get

# Test POST a new console
Invoke-RestMethod -Uri http://localhost:8080/api/favorites/consoles -Method Post -ContentType "application/json" -Body '{"name":"Test Console","description":"Test Description","releaseDate":"2023-01-01","evaluation":4.5}'

# Test DELETE a console
Invoke-RestMethod -Uri "http://localhost:8080/api/favorites/consoles/Test%20Console" -Method Delete

# Test GET all games
Invoke-RestMethod -Uri http://localhost:8080/api/favorites/games -Method Get

# Test POST a new game
Invoke-RestMethod -Uri http://localhost:8080/api/favorites/games -Method Post -ContentType "application/json" -Body '{"name":"Test Game","description":"Test Description","releaseDate":"2023-01-01","evaluation":4.0}'

# Test DELETE a game
Invoke-RestMethod -Uri "http://localhost:8080/api/favorites/games/Test%20Game" -Method Delete

# Test GET all reviews
Invoke-RestMethod -Uri http://localhost:8080/api/reviews -Method Get

# Test POST a new review
Invoke-RestMethod -Uri http://localhost:8080/api/reviews -Method Post -ContentType "application/json" -Body '{"reviewerName":"Tester","gameName":"Test Game","rating":4,"comment":"Great game!"}'
