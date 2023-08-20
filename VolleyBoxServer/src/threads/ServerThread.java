/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import form.ServerForm;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import server.Server;

/**
 *
 * @author HOME
 */
public class ServerThread extends Thread {
    
    private Server server;
    private ServerForm serverForm;

    public ServerThread(ServerForm serverForm) {
        try {
            server = new Server();
            this.serverForm = serverForm;
        } catch (IOException ex) {
            serverForm.getLblInformation().setText("Server has stopped");
            serverForm.getBtnStart().setEnabled(true);
            serverForm.getBtnStop().setEnabled(false);
        }
    }
    
    @Override
    public void run() {
        try {
            serverForm.getLblInformation().setText("Server is running");
            serverForm.getBtnStart().setEnabled(false);
            serverForm.getBtnStop().setEnabled(true);
            server.start();
        } catch (IOException ex) {
            serverForm.getLblInformation().setText("Server has stopped");
            serverForm.getBtnStart().setEnabled(true);
            serverForm.getBtnStop().setEnabled(false);
        }
    }
    
    public void stopThread(){
        try {
            server.stop();
            interrupt();
        } catch (IOException ex) {
            serverForm.getLblInformation().setText("Server has stopped");
            serverForm.getBtnStart().setEnabled(true);
            serverForm.getBtnStop().setEnabled(false);
            interrupt();
        }
    }
    
    
    
}
