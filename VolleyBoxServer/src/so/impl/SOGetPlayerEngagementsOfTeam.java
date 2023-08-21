/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.impl;

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
import so.SOInterface;
import transfer.Response;

/**
 *
 * @author HOME
 */
public class SOGetPlayerEngagementsOfTeam implements SOInterface {

    @Override
    public Response execute(Object object) throws IOException {
        try {
            Team team = (Team) object;
            List<PlayerEngagement> engagements = DBBroker.getInstance().getPlayerEngagementsOfTeam(team);
            return new Response(ServerResponse.OK.OK, engagements);
        } catch (SQLException ex) {
            return new Response(ServerResponse.ERROR, "Cannot return player engagements");
        }
    }

}
