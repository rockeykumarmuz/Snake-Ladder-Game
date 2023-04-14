package com.example.snakegame;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {
    private Circle coin;
    private String name;
    private int currentPosition;

    // making static object of board class
     private static Board gameBoard= new Board();
    // constructor making for player an setting their color tilesize and player name
     public Player(int tileSize, Color coinColor, String playerName) {
        // just radius size it would be
       coin= new Circle(tileSize/2);
       coin.setFill(coinColor);
       currentPosition= 0; // we can set we want but instead of one if we assign anything than one need to handle it
       movePlayer(1);
       name= playerName;
    }

    // making function for playermove
    public void movePlayer(int diceValue) {
        if(currentPosition + diceValue <=100) {
            currentPosition += diceValue;

            // here we are setting move so that we can see how it is transitioning from one position to others
           TranslateTransition secondMove = null, firstMove = translateAnimation(diceValue);

            int newPosition= gameBoard.getNewPosition(currentPosition);
            if(newPosition!=currentPosition && newPosition!=-1) {
                currentPosition = newPosition;
               secondMove = translateAnimation(6);
            }

            if(secondMove==null) {
                firstMove.play();
            } else {
        // we are sequentially transitioning from  one position to another
                SequentialTransition sequentialTransition = new SequentialTransition(firstMove,
                        new PauseTransition(Duration.millis(1000)), secondMove);
                sequentialTransition.play();
            }
        }
        // accessing coordinates  0f X and Y to move into the board
//        int x= gameBoard.getXCoordinates(currentPosition);
//        int y= gameBoard.getYCoordinates(currentPosition);
//
//       // moving x and y coordinates into the board
//        coin.setTranslateX(x);
//        coin.setTranslateY(y);
       // translateAnimation(diceValue);
    }

    // setting animation in the board so that dice can move slowly
    // instead of void i will return TranslateTransition so that we can see the steps while snake bites and ladder transition
    private TranslateTransition translateAnimation(int diceValue) {
        TranslateTransition animate = new TranslateTransition(Duration.millis(200*diceValue), coin);
        animate.setToX(gameBoard.getXCoordinates(currentPosition));
        animate.setToY(gameBoard.getYCoordinates(currentPosition));
        animate.setAutoReverse(false);
        //animate.play();
        return animate;
    }

    // we are setting value of current position as 1 and coin starts from zero as well if we restart the game
    public void startingPosition() {
         // set positon is at zero and move by one after restarting game
         currentPosition=0;
         movePlayer(1);
    }
    boolean isWineer() {
         if(currentPosition==100) {
             return true;
         }
         return false;
    }

    // it i all getter method so that it can put value into the board if it is called
    public Circle getCoin() {
         return coin;
    }

    public int getCurrentPosition() {
         return currentPosition;
    }

    public String getName() {
         return name;
    }

}
