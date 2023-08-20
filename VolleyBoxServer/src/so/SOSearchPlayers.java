/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import database.DBBroker;
import domain.Player;
import enumeration.ServerResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import threads.ClientThread;
import transfer.Response;

/**
 *
 * @author HOME
 */
public class SOSearchPlayers extends SOClass {

    public SOSearchPlayers(ObjectOutputStream out) {
        super(out);
    }

    @Override
    public void execute(Object object) throws IOException {
        try {
            String search = (String) object;
            List<Player> players = DBBroker.getInstance().getPlayersByFirstnameOrLastname(search);
            out.writeObject(new Response(ServerResponse.OK, players));
        } catch (SQLException ex) {
            out.writeObject(new Response(ServerResponse.ERROR, "Cannot return players for by given name"));
        }
    }
    
}
