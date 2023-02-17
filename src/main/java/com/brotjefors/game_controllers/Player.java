package com.brotjefors.game_controllers;

public class Player {

    String name;
    String move;
    public Player () {};

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, String move) {
        this.name = name;
        this.move = move;
    }

    public String getName() {
        return this.name;
    }

    public String getMove() {
        return this.move;
    };

}