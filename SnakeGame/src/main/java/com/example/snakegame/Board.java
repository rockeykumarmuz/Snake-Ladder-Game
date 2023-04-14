package com.example.snakegame;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    ArrayList<Pair<Integer, Integer>> positionCoordinates;

    // arraylist for population snake and ladder postion
    ArrayList<Integer> snakeLadderPosition;

    public Board() {
        positionCoordinates= new ArrayList<>();
        // calling function into the constructor
        populatePositionCoordinates();
        populateSnakeLadder();
    }

    private void populatePositionCoordinates() {
        // adding dummy coordinates
        positionCoordinates.add(new Pair<>(0,0));
        // iterating over the board though for loop

        for (int i = 0; i <SnakeLadder.height ; i++) {
            for (int j=0; j<SnakeLadder.width; j++) {
                int Xcord=0;
                if(i%2==0) {
                     Xcord = j * SnakeLadder.tileSize + (SnakeLadder.tileSize / 2);
                } else{
                    Xcord= SnakeLadder.tileSize*SnakeLadder.height - (j*SnakeLadder.tileSize) - (SnakeLadder.tileSize/2);
                }

               int Ycord= SnakeLadder.tileSize* SnakeLadder.height - (i*SnakeLadder.tileSize) - (SnakeLadder.tileSize/2);
              // adding every position into the arraylist
                positionCoordinates.add(new Pair<>(Xcord, Ycord));
            }
        }
    }

    private void populateSnakeLadder() {
        snakeLadderPosition= new ArrayList<>();

        for(int i=0; i<101; i++) {
            snakeLadderPosition.add(i);
        }
    // manipulating the position of  snake and Ladder
        snakeLadderPosition.set(2, 38);
        snakeLadderPosition.set(8, 31);
        snakeLadderPosition.set(21, 42);
        snakeLadderPosition.set(33, 5);
        snakeLadderPosition.set(46, 84);
        snakeLadderPosition.set(51, 67);
        snakeLadderPosition.set(54, 34);
        snakeLadderPosition.set(63, 16);
        snakeLadderPosition.set(71, 91);
        snakeLadderPosition.set(80, 99);
        snakeLadderPosition.set(93, 74);
        snakeLadderPosition.set(97, 61);

    }

    // setting jumping functionality if dice op is in the range if its snake reach to its tail and if it is at ladder jump to top of it
    public int getNewPosition(int currentPosition) {
        if(currentPosition>0 && currentPosition<=100) {
            return snakeLadderPosition.get(currentPosition);
        }
        return -1;
    }

    // getter method for getting X coordinate for moving into the board
    int getXCoordinates(int position) {
        if(position>=1 && position<=100) {
            return positionCoordinates.get(position).getKey();
        }
        return -1;
    }

    // getter method for getting Y coordinate for moving into the board
    int getYCoordinates(int position) {
        if(position>=1 && position<=100) {
            return positionCoordinates.get(position).getValue();
        }
        return -1;
    }

//    public static void main(String[] args) {
//        Board board= new Board();
//        for(int i=0; i<board.positionCoordinates.size(); i++) {
//            System.out.println(i+" $ x : " + board.positionCoordinates.get(i).getKey()+ " y : " + board.positionCoordinates.get(i).getValue());
//        }
//    }
}
