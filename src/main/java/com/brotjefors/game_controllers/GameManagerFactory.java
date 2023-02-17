package com.brotjefors.game_controllers;

import com.brotjefors.game_controllers.rockPaperScissors.RpsGameManager;

public class GameManagerFactory{

    enum GameType {
        ROCK_PAPER_SCISSORS
    }

    public IGameManager createGameManager(GameType gameType) {
        switch (gameType) {
            case ROCK_PAPER_SCISSORS:
                return new RpsGameManager();
            default:
                throw new IllegalArgumentException("Unsupported game type: " + gameType);
        }
    }
}