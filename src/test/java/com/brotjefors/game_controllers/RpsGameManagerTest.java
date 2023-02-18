package com.brotjefors.game_controllers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import com.brotjefors.game_controllers.rockPaperScissors.RpsGameManager;

public class RpsGameManagerTest {

    @Test
    public void testCreateGame() {
        RpsGameManager manager = new RpsGameManager();
        UUID gameId = manager.createGame(new Player("Karin"));
        assertNotNull(gameId);
    }

    @Test
    public void testCreateGameWithNullPlayer() {
        RpsGameManager manager = new RpsGameManager();

        assertThrows(NullPointerException.class, () -> {
            manager.createGame(null);
        });
    }

    @Test
    public void testCreateGameWithEmptyPlayerName() {
        RpsGameManager manager = new RpsGameManager();

        assertThrows(IllegalArgumentException.class, () -> {
            manager.createGame(new Player(""));
        });
    }

    @Test
    public void testJoinGame() {
        RpsGameManager manager = new RpsGameManager();
        UUID gameId = manager.createGame(new Player("Karin"));
        Object[] response = manager.joinGame(gameId, new Player("Emil"));
        Boolean status = (Boolean)response[1];
        assertTrue(status);
    }

    @Test
    public void testJoinNonexistentGame() {
        RpsGameManager manager = new RpsGameManager();
        UUID gameId = UUID.randomUUID();
        Object[] response = manager.joinGame(gameId, new Player("Emil"));
        Boolean status = (Boolean)response[1];
        assertFalse(status);
    }

    @Test
    public void testJoinFullGame() {
        RpsGameManager manager = new RpsGameManager();
        UUID gameId = manager.createGame(new Player("Karin"));
        manager.joinGame(gameId, new Player("Emil"));
        Object[] response = manager.joinGame(gameId, new Player("Ludvig"));
        Boolean status = (Boolean)response[1];
        assertFalse(status);
    }

    @Test
    public void testMakeMove() {
        RpsGameManager manager = new RpsGameManager();
        UUID gameId = manager.createGame(new Player("Karin"));
        manager.joinGame(gameId, new Player("Emil"));
        manager.makeMove(gameId, new Player("Karin", "ROCK"));
        manager.makeMove(gameId, new Player("Emil", "PAPER"));
        assertTrue(manager.getGame(gameId).getWinner() == "Emil");
    }

    @Test
    public void testMakeInvalidMove() {
        RpsGameManager manager = new RpsGameManager();
        UUID gameId = manager.createGame(new Player("Karin"));
        manager.joinGame(gameId, new Player("Emil"));
        Object[] response = manager.makeMove(gameId, new Player("Karin", "invalid"));
        Boolean status = (Boolean)response[1];
        assertFalse(status);
    }

    @Test
    public void testMakeMoveInvalidPlayer() {
        RpsGameManager manager = new RpsGameManager();
        UUID gameId = manager.createGame(new Player("Karin"));
        manager.joinGame(gameId, new Player("Emil"));
        Object[] response = manager.makeMove(gameId, new Player("Ludvig", "ROCK"));
        Boolean status = (Boolean)response[1];
        assertFalse(status);
    }

    @Test
    public void testMakeMoveWithOnePlayer() {
        RpsGameManager manager = new RpsGameManager();
        UUID gameId = manager.createGame(new Player("Karin"));
        Object[] response =  manager.makeMove(gameId, new Player("Karin", "ROCK"));
        Boolean status = (Boolean)response[1];
        assertFalse(status);
    }

}