
package com.brotjefors.game_controllers.rockPaperScissors;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.brotjefors.game_controllers.IGame;
import com.brotjefors.game_controllers.rockPaperScissors.RpsPlayer.Move;

/**
This class represents a game of Rock-Paper-Scissors between two players.
It contains methods to manage the game's players, the moves they make, and to determine the winner.
*/
public class RpsGame implements IGame{

    private final int MAX_PLAYERS = 2;
    private Map<String, RpsPlayer> players = new HashMap<>();
    private Map<String, Move> moves = new HashMap<>();
    private UUID id;
    private String winner = null;

    /**
     * Constructs a Game with a unique id and the first player.
     *
     * @param id the unique id of the game.
     * @param player the first player in the game.
     */
    public RpsGame(UUID id, RpsPlayer player) {
        this.id = id;
        this.players.put(player.getName(), player);
    }

    /**
     * Adds a player to the game.
     *
     * @param player the player to be added.
     * @return true if successfully added a new player
     */
    public boolean addPlayer(RpsPlayer player) {
        if (players.size() == MAX_PLAYERS) {
            return false;
        }
        this.players.put(player.getName(), player);
        return true;
    }

    /**
     * Gets the players in the game.
     * @return a map of player names and players.
     */
    public Map<String, RpsPlayer> getPlayers() {
        return this.players;
    }

    /**
     * Gets the id of the game.
     * @return the id of the game.
     */
    @Override
    public UUID getId(){
        return this.id;
    }

    /**
     * Sets the id of the game.
     *
     * @param id the id of the game.
     */
    public void setId(UUID id){
        this.id = id;
    }

    /**
     * Gets the winner of the game.
     *
     * @return the winner of the game.
     */
    @Override
    public String getWinner() {
        return this.winner;
    }

    /**
     * Sets the winner of the game.
     *
     * @param winner the winner of the game.
     */
    public void setWinner(String winner) {
        this.winner = winner;
    }

    /**
     * Determines if the game is full, meaning that there are two players in the game.
     *
     * @return true if the game is full, false otherwise.
     */
    public boolean isFull() {
        return players.size() == MAX_PLAYERS;
    }

    /**
     * Determines if both players in the game has made their move
     *
     * @return true if both players have made their move, false otherwise
     */
    public boolean bothMovesMade() {
        return moves.size() == MAX_PLAYERS;
    }

    /**
     * Set the players move in the moves map
     *
     * @param name name of player who made the move
     * @param move move done by the player
     * @return true if the move was succesfull, false otherwise.
     */
    public boolean setMove(String name, Move move) {
        if (moves.size() == MAX_PLAYERS) {
            return false;
        }
        this.moves.put(name, move);
        return true;
    }

    /**
     * Set the player move in the players map
     *
     * @param name name of player
     * @param move move to be set
     */
    public void setPlayerMove(String name, Move move){
        if (!this.players.containsKey(name)) {
            throw new IllegalArgumentException("Player " + name + " does not exist in the game.");
        }
        this.players.get(name).setMove(move);
    }

    /**
     * Updates the moves in the players map for all players.
     */
    public void updatePlayerMoves() {
        for (Map.Entry<String, Move> move : this.moves.entrySet()) {
            try {
                String key = move.getKey();
                RpsPlayer player = this.players.get(key);
                if (player != null) {
                    player.setMove(move.getValue());
                } else {
                    throw new IllegalArgumentException("No player found with name: " + move.getKey());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Determines the winner of the game.
     *
     * @return name of the winner
     */
    public String decideWinner() {

        try {
            Collection<RpsPlayer> values = this.players.values();
            RpsPlayer[] valuesArray = values.toArray(new RpsPlayer[values.size()]);

            if (valuesArray.length != 2) {
                throw new IllegalStateException("There should be two players in the game.");
            }

            RpsPlayer player1 = valuesArray[0];
            RpsPlayer player2 = valuesArray[1];

            String name1 = player1.getName();
            String name2 = player2.getName();
            Move move1 = player1.getMove();
            Move move2 = player2.getMove();

            if (move1 == Move.UNDETERMINED || move2 == Move.UNDETERMINED) {
                return "UNDETERMINED";
            }

            if (move1 == move2) {
                return "DRAW";
            }

            switch (move1) {
                case ROCK:
                    return (move2 == Move.SCISSORS) ? name1 : name2;
                case PAPER:
                    return (move2 == Move.ROCK) ? name1 : name2;
                case SCISSORS:
                    return (move2 == Move.PAPER) ? name1 : name2;
                default:
                    return "UNDETERMINED";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "UNDETERMINED";
        }
    }
}
