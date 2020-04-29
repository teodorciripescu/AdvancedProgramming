package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class GameClient {
    public static final String serverAddress = "127.0.0.1"; // adresa IP a serverului
    public static final int PORT = 3000; // portul serverului

    public void connect() throws IOException {
        //cream un socket
        //BufferedReader in - ne ajuta sa citim raspunsurile de la server
        //PrintWriter out - il folosim pentru a scrie comenzi catre server
        try (Socket socket = new Socket(serverAddress, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))){

            BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));
            while(true){
                //citim de la tastatura comanda data de client
                String line=buffer.readLine();

                String request = line.trim();
                if(request.equals("exit")){
                    socket.close();
                    System.exit(0);
                }
                //trimitem comanda catre server
                out.println(request);
                //citim raspunsul serverului
                String response = in.readLine();
                System.out.println(response);
                if(response.equals("Server stopped")){
                    socket.close();
                    System.exit(0);
                }
            }
        }catch(UnknownHostException e){
            System.err.println("No server listening: " + e);
        }
    }
}
