package com.example.snakegame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends Application {

    public static final int tileSize=40, width=10, height=10;

    // defining button dimension and information to be displayed on respective button
    public static final int buttonLine= height*tileSize+ 30, infoLine= buttonLine-30;
    // we are creating content inside this and returning to the scene

    // making variable for button controlling while dice rolling
    private boolean gameStarted= false,  playerOneTurn=false, playerTwoTurn=false;

    // making object of dice class
    private static Dice dice=new Dice();
    private Player playerOne, playerTwo;
    private Pane createContent() {
        Pane root= new Pane();
        // setting preferreble size of pane
        root.setPrefSize(tileSize*width, tileSize*height + 100);
        // calling Tile class and passing parameter as a tilesize

        // Making just multiple box like in sanke ludo game
        for(int i=0; i<height; i++) {
            for(int j=0;j<width; j++) {

                Tile tile= new Tile(tileSize);
                // setting tile coordinates
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().add(tile);
           }
        }

        // taking an image into the area
        Image img= new Image("C:\\Users\\rocke\\IdeaProjects\\SnakeGame\\src\\main\\sgpic.png");
        // and displaying image into the particular area
        ImageView board= new ImageView();
        board.setImage(img); // setting image
        board.setFitHeight(height*tileSize); // setting image into the fit height
        board.setFitWidth(width*tileSize); // setting image into the fit width

        // adding Button to the board below board
        Button playerOneButton= new Button("Player One");
        Button playerTwoButton= new Button("Player Two");
        Button startButton= new Button("Start");

        // set button dimension and translation
        playerOneButton.setTranslateY(buttonLine);
        playerOneButton.setTranslateX(20);
        playerOneButton.setDisable(true);

        playerTwoButton.setTranslateY(buttonLine);
        playerTwoButton.setTranslateX(300);
        playerTwoButton.setDisable(true);

        startButton.setTranslateY(buttonLine);
        startButton.setTranslateX(180);

        // adding information to each button working
        Label playerOneLabel= new Label(""); // initially it becomes empty agfer start it shoeing something your turn my turn
        Label playerTwoLabel= new Label("");
        Label diceLabel= new Label("Start the Game");

        // setting each level  and information dimension
        playerOneLabel.setTranslateY(infoLine);
        playerOneLabel.setTranslateX(20);

        playerTwoLabel.setTranslateY(infoLine);
        playerTwoLabel.setTranslateX(300);

        diceLabel.setTranslateY(infoLine);
        diceLabel.setTranslateX(160);

        // initializing tha value of playerOne and player two and also setting all the required value
        playerOne= new Player(tileSize, Color.BLACK, "Amit");
        //just handling overlap cases in the dice if it is at same cell
        playerTwo= new Player(tileSize-5, Color.RED, "Rockey");

        // we are handling for player one chance is coming then need to press  button
        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted) {
                    if (playerOneTurn) {
                        int dicevalue = dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value : " + dicevalue);
                        playerOne.movePlayer(dicevalue);

                        // winning conditions
                        if (playerOne.isWineer()) {
                            diceLabel.setText("Winner is " + playerOne.getName());
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTwoTurn = true;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Restart");

                        } else {

                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTwoTurn = true;
                            playerTwoButton.setDisable(false);
                            playerTwoLabel.setText("Your Turn " + playerTwo.getName());
                        }
                    }
                }

            }
        });

        // we are handling for player two chance is coming then
        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted) {
                    if(playerTwoTurn) {
                        int dicevalue = dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value : " + dicevalue);
                        playerTwo.movePlayer(dicevalue);

                        // winning conditions
                        if (playerTwo.isWineer()) {
                            diceLabel.setText("Winner is "+playerTwo.getName());
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTwoTurn = true;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Restart");

                        } else {
                            playerOneTurn = true;
                            playerOneButton.setDisable(false);
                            playerOneLabel.setText("Your Turn " + playerOne.getName());

                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");

                        }
                    }
                }
            }
        });


        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted= true;
                diceLabel.setText("Game Started");
                startButton.setDisable(true);
                playerOneTurn = true;
                playerOneLabel.setText("Your Turn "+ playerOne.getName());
                playerOneButton.setDisable(false);
                playerOne.startingPosition(); // initializing position again

                playerTwoTurn= false;
                playerTwoLabel.setText("");
                playerTwoButton.setDisable(true);
                playerTwo.startingPosition(); // initializing position again
            }
        });
        // adding children button into the board like buttonOne button two and start button
        root.getChildren().addAll(
                board, playerOneButton, playerTwoButton, startButton,
                playerOneLabel, playerTwoLabel, diceLabel,
                playerOne.getCoin(), playerTwo.getCoin()
        );

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake and Ladder!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}