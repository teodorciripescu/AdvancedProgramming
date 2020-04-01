package com.company;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Board {
    public static List<Token> tokens;
    private int n;
    public static boolean available = true;
    public Board(int n){
        this.n = n;
        // making tokens a synchronized list
        tokens =  Collections.synchronizedList(new LinkedList<>());
        // adding tokens to the list (with values from 1 to n)
        for (int i = 1; i <= n; i++) {
            tokens.add(new Token(i));
        }
    }

    public static synchronized int getNumberOfTokensOnBoard() {
        return tokens.size();
    }
    public static synchronized void setAvailable(boolean a){
        available = a;
    }
    public static synchronized boolean getAvailable(){
        return available;
    }
    public Token getTokenById(int id){
        return tokens.get(id);
    }
}
