package com.example.snakegame;

public class Dice {
    public int getRolledDiceValue() {
        return (int)(Math.random()*(6-1+1)+1);
    }
}
