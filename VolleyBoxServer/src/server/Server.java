/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import threads.ClientThread;
import threads.ServerThread;

/**
 *
 * @author HOME
 */
public class Server {

    private ServerSocket ss;
    private static List<ClientThread> clients;

    public Server() throws IOException {
        ss = new ServerSocket(6666);
        clients = new ArrayList<>();
    }

    public void start() throws IOException {
        while (true) {
            Socket socket = ss.accept();
            ClientThread ct = null;
            try {
                ct = new ClientThread(socket);
            } catch (IOException e) {
                ct = null;
            }
            if(ct != null){
                clients.add(ct);
                ct.start();
            }
        }
    }

    public void stop() throws IOException {
        for (ClientThread client : clients) {
            client.terminate();
            client.interrupt();
        }
        ss.close();
    }

    public static void removeClient(ClientThread client) {
        clients.remove(client);
    }

}
