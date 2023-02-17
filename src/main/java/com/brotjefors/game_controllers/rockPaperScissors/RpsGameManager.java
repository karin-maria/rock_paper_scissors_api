
package com.brotjefors.game_controllers.rockPaperScissors;

import java.util.UUID;

import com.brotjefors.game_controllers.IGame;
import com.brotjefors.game_controllers.IGameManager;
import com.brotjefors.game_controllers.Player;
import com.brotjefors.game_controllers.rockPaperScissors.RpsPlayer.Move;

import java.util.HashMap;
import java.util.Map;

/**
This class is responsible for managing a Rock, Paper, Scissors games.
*/
public class RpsGameManager implements IGameManager {

    // A map of UUIDs to RpsGame objects to keep track of ongoing games.
    private Map<UUID, RpsGame> games = new HashMap<>();

    /**
     * Helper method for creating a response object with a message and status.
     *
     * @param message a message to include in the response
     * @param status a boolean indicating whether the response was successful or not
     * @return an Object array containing the message and status
     */
    private Object[] createResponse(String message, Boolean status) {
        Object[] response = {message, status};
        return response;
    }

    /**
     * Creates a new Rock, Paper, Scissors game with a single player.
     *
     * @param player the player to create the game with
     * @return the UUID of the new game or null if the player is null, has no name or the name is empty
     */
    @Override
    public UUID createGame(Player player) {
        RpsPlayer rpsPlayer = new RpsPlayer(player.getName());
        if (player == null || player.getName() == null || player.getName().isEmpty()) {
            return null;
        }

        rpsPlayer.setMove(Move.UNDETERMINED);

        UUID gameId = UUID.randomUUID();
        RpsGame game = new RpsGame(gameId, rpsPlayer);

        games.put(gameId, game);

        return gameId;
    }

    /**
     * Retrieves the game with the specified UUID.
     *
     * @param id the UUID of the game to retrieve
     * @return the game with the specified UUID or null if the game does not exist
     */
    @Override
    public IGame getGame(UUID id) {
        return games.get(id);
    }

    /**
     * Adds a player to an existing game.
     *
     * @param id the UUID of the game to add the player to
     * @param player the player to add to the game
     * @return an Object array containing a message and status indicating whether the player was successfully added
     *         to the game or not
     */
    @Override
    public Object[] joinGame(UUID id, Player player) {
        RpsGame game = games.get(id);
        RpsPlayer rpsPlayer = new RpsPlayer(player.getName());
        if (game == null) {
            return createResponse("Game is null.", false);
        }
        rpsPlayer.setMove(Move.UNDETERMINED);
        if (game.addPlayer(rpsPlayer)) {
            return createResponse(" joined the game!", true);
        }else {
            return createResponse(" game is full.", false);
        }

    }

    /**
     * Sets the move of a player in a game.
     *
     * @param id the UUID of the game to make the move in
     * @param player the player making the move
     * @return an Object array containing a message and status indicating whether the move was successfully made or not
     */
    @Override
    public Object[] makeMove(UUID id, Player player) {
        RpsGame game = games.get(id);
        if (game == null || !game.isFull()) {
            return createResponse("Game is not ready to make a move.", false);
        }
        Move move = Move.UNDETERMINED;
        try {
            move = Move.valueOf(player.getMove());
        } catch (IllegalArgumentException e) {
            return createResponse("Invalid move.", false);
        }
        boolean status = game.setMove(player.getName(), move);
        if(!status){
            return createResponse("Both moves are already made.", false);
        }

        // Player move is HIDDEN until both moves are made.
        game.setPlayerMove(player.getName(), Move.HIDDEN);

        // Update player moves if both moves are made.
        if (game.bothMovesMade()) {
            game.updatePlayerMoves();
            game.setWinner(game.decideWinner());
            return createResponse("Successfully made both moves. Winner is decided.", true);
        }
        return createResponse("Successfully made a move. Waiting for other move...", true);
    }
}

