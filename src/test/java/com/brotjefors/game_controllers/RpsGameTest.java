package com.brotjefors.game_controllers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import com.brotjefors.game_controllers.rockPaperScissors.RpsGame;
import com.brotjefors.game_controllers.rockPaperScissors.RpsPlayer;
import com.brotjefors.game_controllers.rockPaperScissors.RpsPlayer.Move;

class RpsGameTest {

    @Test
    void testAddPlayer() {
        RpsGame game = new RpsGame(UUID.randomUUID(), new RpsPlayer("Karin"));
        assertEquals(1, game.getPlayers().size());

        game.addPlayer(new RpsPlayer("Emil"));
        assertEquals(2, game.getPlayers().size());
    }

    @Test
    void testIsFull() {
        RpsGame game = new RpsGame(UUID.randomUUID(), new RpsPlayer("Karin"));
        assertFalse(game.isFull());

        game.addPlayer(new RpsPlayer("Emil"));
        assertTrue(game.isFull());
    }

    @Test
    void testBothMovesMade() {
        RpsGame game = new RpsGame(UUID.randomUUID(), new RpsPlayer("Karin"));
        game.addPlayer(new RpsPlayer("Emil"));
        assertFalse(game.bothMovesMade());

        game.setMove("Karin", Move.ROCK);
        assertFalse(game.bothMovesMade());

        game.setMove("Emil", Move.SCISSORS);
        assertTrue(game.bothMovesMade());
    }

    @Test
    void testSetMove() {
        RpsGame game = new RpsGame(UUID.randomUUID(), new RpsPlayer("Karin"));
        game.addPlayer(new RpsPlayer("Emil"));

        game.setMove("Karin", Move.ROCK);
        game.setMove("Emil", Move.SCISSORS);
        game.updatePlayerMoves();

        assertEquals(Move.ROCK, game.getPlayers().get("Karin").getMove());
        assertEquals(Move.SCISSORS, game.getPlayers().get("Emil").getMove());
    }

    @Test
    public void testSetPlayerMove() {
        RpsGame game = new RpsGame(UUID.randomUUID(), new RpsPlayer("Karin"));
        game.addPlayer(new RpsPlayer("Emil"));

        game.setPlayerMove("Karin", Move.ROCK);
        game.setPlayerMove("Emil", Move.PAPER);

        assertEquals(Move.ROCK, game.getPlayers().get("Karin").getMove());
        assertEquals(Move.PAPER, game.getPlayers().get("Emil").getMove());
    }

    @Test
    public void testSetPlayerMoveInvalidName() {
        RpsGame game = new RpsGame(UUID.randomUUID(), new RpsPlayer("Karin"));

        assertThrows(IllegalArgumentException.class, () -> {
            game.setPlayerMove("Emil", Move.PAPER);
        });
    }

    @Test
    void testDecideWinner() {

        String winner;
        RpsGame game = new RpsGame(UUID.randomUUID(), new RpsPlayer("Karin"));
        game.addPlayer(new RpsPlayer("Emil"));
        game.setMove("Karin", Move.ROCK);
        game.setMove("Emil", Move.SCISSORS);
        game.updatePlayerMoves();
        winner = game.decideWinner();
        assertEquals("Karin", winner);

        game = new RpsGame(UUID.randomUUID(), new RpsPlayer("Karin"));
        game.addPlayer(new RpsPlayer("Emil"));
        game.setMove("Karin", Move.SCISSORS);
        game.setMove("Emil", Move.PAPER);
        game.updatePlayerMoves();
        winner = game.decideWinner();
        assertEquals("Karin", winner);

        game = new RpsGame(UUID.randomUUID(), new RpsPlayer("Karin"));
        game.addPlayer(new RpsPlayer("Emil"));
        game.setMove("Karin", Move.PAPER);
        game.setMove("Emil", Move.ROCK);
        game.updatePlayerMoves();
        winner = game.decideWinner();
        assertEquals("Karin", winner);

        game = new RpsGame(UUID.randomUUID(), new RpsPlayer("Karin"));
        game.addPlayer(new RpsPlayer("Emil"));
        game.setMove("Karin", Move.ROCK);
        game.setMove("Emil", Move.ROCK);
        game.updatePlayerMoves();
        winner = game.decideWinner();
        assertEquals("DRAW", winner);

        // TODO add rest of combinations.
    }

}