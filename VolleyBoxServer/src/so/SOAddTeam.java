/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import database.DBBroker;
import domain.Team;
import enumeration.ServerResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import threads.ClientThread;
import transfer.Response;

/**
 *
 * @author HOME
 */
public class SOAddTeam extends SOClass {

    public SOAddTeam(ObjectOutputStream out) {
        super(out);
    }

    @Override
    public void execute(Object object) throws IOException {
        try {
            Team team = (Team) object;
            DBBroker.getInstance().addTeam(team);
            out.writeObject(new Response(ServerResponse.OK, null));
        } catch (SQLException ex) {
            out.writeObject(new Response(ServerResponse.ERROR, "Team not successfully added"));
        }
    }
    
    
}
