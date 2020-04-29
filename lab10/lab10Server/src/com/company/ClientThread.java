package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientThread extends Thread{
    private Socket socket = null ;
    public ClientThread(Socket socket){this.socket = socket;}
    public void run(){
        try {
            boolean serverStopped = false;
            // cream buffer-ul pentru input (ce primim de la client)
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // cream buffer-ul pentru output (ce trimite servarul catre client)
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            while(true){
                //Citim ce a trimis clientul
                String request = in.readLine();
                //Formulam raspunsul catre client
                String response = "Server received the request \'" + request + "\'";
                if(request.equals("stop")){
                    response = "Server stopped";
                    serverStopped = true;
                }
                out.println(response);
                out.flush();
                if(serverStopped){
                    System.out.println("Server stopping...");
                    System.exit(0);
                }
            }
        }catch(IOException e){
            System.err.println("I/O Err: " + e);
        }finally{
            try{
                socket.close();
            }catch(IOException e){
                System.err.println (e);
            }
        }
    }
}