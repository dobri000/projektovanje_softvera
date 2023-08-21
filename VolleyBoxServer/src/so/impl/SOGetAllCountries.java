/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.impl;

import database.DBBroker;
import domain.Country;
import enumeration.ServerResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.SOInterface;
import threads.ClientThread;
import transfer.Response;

/**
 *
 * @author HOME
 */
public class SOGetAllCountries implements SOInterface {

    @Override
    public Response execute(Object object) throws IOException {
        try {
            List<Country> countries = DBBroker.getInstance().getAllCountries();
            return new Response(ServerResponse.OK, countries);
        } catch (SQLException ex) {
            return new Response(ServerResponse.ERROR, "Cannot return all countries");
        }
    }

}
