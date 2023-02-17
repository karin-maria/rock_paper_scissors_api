package com.brotjefors.game_controllers;

import java.util.UUID;

public interface IGameManager {

    UUID createGame(Player player);

    IGame getGame(UUID id);

    Object[] joinGame(UUID id, Player player);

    Object[] makeMove(UUID id, Player player);

}