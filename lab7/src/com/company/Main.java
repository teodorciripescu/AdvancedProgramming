package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Board b = new Board(10);
        Game g = new Game(b,3,2);
        g.startGame();
    }
}
