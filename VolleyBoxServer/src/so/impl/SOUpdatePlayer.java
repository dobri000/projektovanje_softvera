/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.impl;

import database.DBBroker;
import domain.Player;
import enumeration.ServerResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.SOInterface;
import threads.ClientThread;
import transfer.Response;

/**
 *
 * @author HOME
 */
public class SOUpdatePlayer implements SOInterface {

    @Override
    public Response execute(Object object) throws IOException {
        try {
            Player player = (Player) object;
            DBBroker.getInstance().updatePlayer(player);
            return new Response(ServerResponse.OK, null);
        } catch (SQLException ex) {
            return new Response(ServerResponse.ERROR, "Cannot update player");
        }
    }
    
}
