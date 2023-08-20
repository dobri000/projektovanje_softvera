/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import database.DBBroker;
import domain.Hall;
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
public class SOSearchHalls extends SOClass {

    public SOSearchHalls(ObjectOutputStream out) {
        super(out);
    }

    @Override
    public void execute(Object object) throws IOException {
        try {
            String search = (String) object;
            List<Hall> halls = DBBroker.getInstance().findHallsSearch(search);
            out.writeObject(new Response(ServerResponse.OK, halls));
        } catch (SQLException ex) {
            out.writeObject(new Response(ServerResponse.ERROR, "Cannot return halls for by given name"));
        }
    }
    
}
