/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.impl;

import database.DBBroker;
import domain.Admin;
import java.io.IOException;
import so.SOInterface;
import transfer.Response;

/**
 *
 * @author HOME
 */
public class SOQuit implements SOInterface {

    @Override
    public Response execute(Object object) throws IOException {
        Admin admin = (Admin) object;
        DBBroker.getInstance().logout(admin);
        return null;
    }
    
    
    
}
