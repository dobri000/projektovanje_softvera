/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.impl;

import database.DBBroker;
import domain.Admin;
import enumeration.ServerResponse;
import java.io.IOException;
import so.SOInterface;
import transfer.Response;

/**
 *
 * @author HOME
 */
public class SOLogout implements SOInterface {

    @Override
    public Response execute(Object object) throws IOException {
        Admin admin = (Admin) object;
        boolean success = DBBroker.getInstance().logout(admin);
        if(success) {
            return new Response(ServerResponse.OK, "Successfully logged out");
        } else {
            return new Response(ServerResponse.ERROR, "Admin cannot log out");
        }
    }
    
}
