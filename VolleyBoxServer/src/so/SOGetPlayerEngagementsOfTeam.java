/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import database.DBBroker;
import domain.PlayerEngagement;
import domain.Team;
import enumeration.ServerResponse;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.Response;

/**
 *
 * @author HOME
 */
public class SOGetPlayerEngagementsOfTeam extends SOClass {

    public SOGetPlayerEngagementsOfTeam(ObjectOutputStream out) {
        super(out);
    }

    @Override
    public void execute(Object object) throws IOException {
        try {
            Team team = (Team) object;
            List<PlayerEngagement> engagements = DBBroker.getInstance().getPlayerEngagementsOfTeam(team);
            out.writeObject(new Response(ServerResponse.OK.OK, engagements));
        } catch (SQLException ex) {
            out.writeObject(new Response(ServerResponse.ERROR, "Cannot return player engagements"));
        }
    }

}
