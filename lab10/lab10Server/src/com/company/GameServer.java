package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer{
    // portul la care asculta serverul
    public static final int PORT = 3000;

    public GameServer() throws IOException{
        //socketul serverului
        ServerSocket serverSocket = null;
        try{
            //instantiem socketul la portul dat
            serverSocket = new ServerSocket(PORT);
            //mereu asteptam clienti noi
            while(true){
                //asteptam pentru un client(caracter blocant)
                System.out.println("Waiting for a client ...");
                Socket socket = serverSocket.accept();
                System.out.println(socket.getRemoteSocketAddress() + " entered on the server!");
                // executam cererea clientului intr-un nou thread
                new ClientThread(socket).start();
            }
        }catch(IOException e){
            System.err.println("Err: " + e);
        }finally{
            //in final inchidem socketul serverului
            serverSocket.close();
        }
    }
}
