package com.company;

public class Game {
    private Board b;
    public static int k; // the aritmetic progression's size
    private Player[] players;
    private static boolean gameOver = false;
    private Thread[] threads;
    public Game(Board b, int k, int numberOfPlayers){
        this.b = b;
        this.k = k;
        players = new Player[numberOfPlayers];

        for (int i = 0; i < numberOfPlayers; i++) {
            players[i] = new Player("Player"+(i+1));
        }
    }

    public static synchronized boolean isGameOver() {
        return gameOver;
    }

    public static synchronized void setGameOver(boolean gameOver) {
        Game.gameOver = gameOver;
    }

    public void startGame(){
        //create a thread for every player
        for (int i = 0; i < players.length; i++) {
            new Thread(players[i]).start();
            System.out.println("Started thread for Player "+(i+1));

        }

    }

}
