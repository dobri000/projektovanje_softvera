/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import database.DBBroker;
import domain.StaffMember;
import enumeration.ServerResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.Response;

/**
 *
 * @author HOME
 */
public class SODeleteStaffMember extends SOClass {

    public SODeleteStaffMember(ObjectOutputStream out) {
        super(out);
    }

    @Override
    public void execute(Object object) throws IOException {
        try {
            StaffMember staff = (StaffMember) object;
            DBBroker.getInstance().deleteStaffMember(staff);
            out.writeObject(new Response(ServerResponse.OK, null));
        } catch (SQLException ex) {
            out.writeObject(new Response(ServerResponse.ERROR, "Staff memeber not successfully deleted"));
        }
    }
    
}
