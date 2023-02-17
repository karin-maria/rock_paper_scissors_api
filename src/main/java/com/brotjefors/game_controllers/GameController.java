package com.brotjefors.game_controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.brotjefors.game_controllers.GameManagerFactory.GameType;

import java.util.UUID;

@RestController
public class GameController {

    private GameManagerFactory gameFactory = new GameManagerFactory();
    private IGameManager gameManager = gameFactory.createGameManager(GameType.ROCK_PAPER_SCISSORS);

    /**
     * Create a new game.
     *
     * @param player The player who creates the game.
     * @return A ResponseEntity that contains a string message and an HTTP status code.
     */
    @PostMapping("/games")
    public ResponseEntity<String> createGame(@RequestBody Player player) {
        if (player == null || player.getName() == null || player.getName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please provide valid player information in the request body");
        }

        UUID gameId = gameManager.createGame(player);

        return ResponseEntity.status(HttpStatus.CREATED).body("A new game has been created. Game ID is: " + gameId + "\n");
    }

    /**
     * Retrieve an existing game by its ID.
     *
     * @param id The ID of the game to retrieve.
     * @return A ResponseEntity that contains the game object and an HTTP status code.
     */
    @GetMapping("/games/{id}")
    public ResponseEntity<IGame> getGame(@PathVariable UUID id) {
        IGame game = gameManager.getGame(id);
        if (game == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    /**
     * Allow a player to join an existing game.
     *
     * @param id The ID of the game to join.
     * @param player The player who wants to join the game.
     * @return A ResponseEntity that contains a string message and an HTTP status code.
     */
    @PostMapping("/games/{id}/join")
    public ResponseEntity<String> joinGame(@PathVariable UUID id, @RequestBody Player player) {
        Object[] response = gameManager.joinGame(id, player);
        String msg = (String)response[0];
        if (!(Boolean)response[1]) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Joining failed: " + msg + "\n");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Player " + player.getName() + msg + " with id: " + id + "\n");
    }

    /**
     * Allow a player to make a move in an existing game.
     *
     * @param id The ID of the game to make a move in.
     * @param player The player who wants to make a move.
     * @return A ResponseEntity that contains a string message and an HTTP status code.
     */
    @PostMapping("/games/{id}/move")
    public ResponseEntity<String> makeMove(@PathVariable UUID id, @RequestBody Player player) {
        Object[] response = gameManager.makeMove(id, player);
        String msg = (String)response[0];
        if (!(Boolean)response[1]) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg + ".\n");
        }
        return ResponseEntity.ok(player.getName() + " made a move!\n");
    }
}

