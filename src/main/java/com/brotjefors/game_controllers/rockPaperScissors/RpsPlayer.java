package com.brotjefors.game_controllers.rockPaperScissors;

/**
 * This class represents a player in a rock-paper-scissors game.
 */
public class RpsPlayer {

    private String name;
    private Move move;

    /**
     * The possible moves in a rock-paper-scissors game.
     */
    public enum Move {
        UNDETERMINED,
        HIDDEN,
        ROCK,
        PAPER,
        SCISSORS
    }

    public RpsPlayer () {};

    /**
     * Creates a new player with the specified name.
     *
     * @param name The name of the player
     * @throws IllegalArgumentException if name is null or empty
     */
    public RpsPlayer(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name of Player cannot be null or empty");
        }
        this.name = name;
        this.move = Move.UNDETERMINED;
    }

    /**
     * Creates a new player with the specified name and move.
     *
     * @param name The name of the player
     * @param move The move of the player
     * @throws IllegalArgumentException if name is null or empty
     */
    public RpsPlayer(String name, Move move) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name of Player cannot be null or empty");
        }
        this.name = name;
        this.move = move;
    }

    /**
     * Gets the name of the player.
     *
     * @return The name of the player
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the player.
     *
     * @param name The name of the player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the move of the player.
     *
     * @return The move of the player
     */
    public Move getMove() {
        return this.move;
    }

    /**
     * Sets the move of the player.
     *
     * @param move The move of the player
     */
    public void setMove(Move move) {
        this.move = move;
    }
}