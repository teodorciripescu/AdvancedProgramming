package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        GameClient client = new GameClient();
        try {
            client.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
