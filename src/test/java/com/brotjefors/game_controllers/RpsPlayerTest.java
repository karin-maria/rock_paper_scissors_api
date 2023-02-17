package com.brotjefors.game_controllers;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.brotjefors.game_controllers.rockPaperScissors.RpsPlayer;

public class RpsPlayerTest {

    @Test
    public void testNewPlayer() {
        RpsPlayer player = new RpsPlayer("Karin");
        assertEquals("Karin", player.getName());
    }

    @Test
    public void testNewPlayerWithEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new RpsPlayer("");
        });
    }

    @Test
    public void testNewPlayerWithNullName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new RpsPlayer(null);
        });
    }

    @Test
    public void testSetAndGetName() {
        RpsPlayer player = new RpsPlayer("Karin");
        player.setName("Emil");
        assertEquals("Emil", player.getName());
    }

    @Test
    public void testSetAndGetMove() {
        RpsPlayer player = new RpsPlayer("Karin");
        player.setMove(RpsPlayer.Move.ROCK);
        assertEquals(RpsPlayer.Move.ROCK, player.getMove());
    }
}
