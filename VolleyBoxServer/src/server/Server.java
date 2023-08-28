/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import database.DBBroker;
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
    }

    public void start() throws IOException {
        clients = new ArrayList<>();
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
            client.serverStop();
            client.interrupt();
        }
        ss.close();
        DBBroker.getInstance().emptyList();
    }

    public static void removeClient(ClientThread client) {
        clients.remove(client);
    }

}
