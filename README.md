# Rock Paper Scissors Game API
This is a RESTful API that allows users to play Rock, Paper, Scissors. The API is implemented using Java and Spring Boot. The following instructions will explain how to use the API.

## Prerequisites
- Java 17
- Gradle

## How to run the API
1. Clone this repository to your local machine.
`git clone https://github.com/karin-maria/rock_paper_scissors_api.git`
2. Open a terminal window and navigate to the root of the cloned repository.
3.  Alt 1: Run the server from the application folder with `./gradlew bootRun`
    Alt. 2: Build the jar file with `./gradlew build` and run it with `java -jar build/libs/games-0.0.1-SNAPSHOT.jar`

    The server starts and runs on http://localhost:8080

## API Endpoints
The following endpoints are available:

```
POST /games
GET /games/<id>
POST /games/<id>/join
POST /games/<id>/move
```

### Create a new game `POST /games`
To create a new game, make a POST request to /games. You must provide a JSON object containing a player's name:

Request Body:
```
{
"name": "player name"
}
```

Response Body:
```
A new game has been created. Game ID is: <id>
```

Example using curl:
```
curl -X POST \
  http://localhost:8080/games \
  -H "Content-Type:application/json" \
  -d '{"name": "Karin"}'
```


### Get game status `GET /games/<id>`
To get a game, make a GET request to /games/{id}, where {id} is the ID of the game you want to retrieve.

Response Body:
```
{
"id": "<id>",
"status": "<game_status>",
"players": [
    {
    "name": "player1_name",
    "move": "<player1_move>"
    },
    {
    "name": "player2_name",
    "move": "<player2_move>"
    }
],
"winner": "<winner_name>"
}
```

### Join an existing game `POST /games/<id>/join`
To join a game, make a POST request to /games/{id}/join, where {id} is the ID of the game you want to join. You must provide a JSON object containing a player's name:

Request Body:
```
{
"name": "player name"
}
```

Response Body:
```
Player <player_name> joined the game with id: <id>
```

Example using curl:
```
curl -X POST \
  http://localhost:8080/games/${id}/join \
  -H "Content-Type:application/json" \
  -d '{"name": "Emil"}'
```


### Make a move `POST /games/<id>/move`
To make a move, make a POST request to /games/{id}/move, where {id} is the ID of the game you want to make a move in. You must provide a JSON object containing the player's name and their move:


Request Body:
```
{
"name": "player name",
"move": "ROCK/PAPER/SCISSORS"
}
```

Response Body:
```
<player_name> made a move!
```

Example using curl:
```
curl -X POST \
  http://localhost:8080/games/${id}/move \
  -H "Accept: application/json" \
  -H "Content-Type:application/json" \
  -d '{"name": "Karin", "move": "ROCK"}'

curl -X POST \
  http://localhost:8080/games/${id}/move \
  -H "Accept: application/json" \
  -H "Content-Type:application/json" \
  -d '{"name": "Emil", "move": "PAPER"}'
```