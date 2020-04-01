package com.company;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Player implements Runnable {
    private String name;
    private List<Token> myTokens;
    private int remainingTokens = 1;
    public Player(String name){
        this.name = name;
        myTokens = new LinkedList<>();
    }
    @Override
    public void run() {
        while(Board.getNumberOfTokensOnBoard()!=0 && Game.isGameOver()==false) {
            if(Game.isGameOver()){
                break;
            }
            extractToken();

        }
    }

    public synchronized void extractToken(){
        while(!Board.getAvailable()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //Board.setAvailable(false);
        Random rn = new Random();
        remainingTokens = Board.getNumberOfTokensOnBoard();
        int id = rn.nextInt(remainingTokens);
        if(remainingTokens!=0 && id<remainingTokens-1) {

            System.out.println("Random number(" + name + "): " + (id));
            //choose the token to return
            Token t = Board.tokens.get(id);
            //remove it from the table
            Board.tokens.remove(id);
            //give the token to the player
            myTokens.add(t);

            StringBuilder sbBoard = new StringBuilder("Tokens remaining on the board: ");

            for (int i = 0; i < Board.tokens.size(); i++) {
                sbBoard.append(Board.tokens.get(i).getValue() + " ");
            }
            System.out.println(sbBoard.toString());

            StringBuilder sb = new StringBuilder(name + "'s tokens:");

            for (int i = 0; i < myTokens.size(); i++) {
                sb.append(myTokens.get(i).getValue() + " ");
            }
            System.out.println(sb.toString());
            if(verifyProgression()){
                Game.setGameOver(true);
            }
            Board.setAvailable(true);
            notifyAll();
        }
    }

    private synchronized boolean verifyProgression(){

        try {
            //check if the player drawn enough tokens
            if(myTokens.size()>=Game.k){
                //sort in ascending order
                myTokens.sort(new Comparator<Token>() {
                    @Override
                    public int compare(Token o1, Token o2) {
                        return o1.getValue() - o2.getValue();
                    }
                });
                //get the difference between the value of 2 tokens
                int difference = myTokens.get(1).getValue() - myTokens.get(0).getValue();
                //check in all the tokens are in a progression
                for (int i = 1; i < myTokens.size() - 1; i++) {
                    if (myTokens.get(i + 1).getValue() - myTokens.get(i).getValue() != difference) {
                        return false;
                    }
                }
                System.out.println(name + " won!");
                return true;
            }
            else return  false;
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
