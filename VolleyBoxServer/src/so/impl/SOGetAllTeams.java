/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.impl;

import database.DBBroker;
import domain.Team;
import enumeration.ServerResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
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
public class SOGetAllTeams implements SOInterface {

    @Override
    public Response execute(Object object) throws IOException {
        try {
            List<Team> teams = DBBroker.getInstance().getAllTeams();
            return new Response(ServerResponse.OK, teams);
        } catch (SQLException ex) {
            return new Response(ServerResponse.ERROR, "Cannot return all teams");
        }
    }

}
