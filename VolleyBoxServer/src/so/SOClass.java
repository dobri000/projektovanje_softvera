/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;

/**
 *
 * @author HOME
 */
public abstract class SOClass {
    
    protected ObjectOutputStream out;

    public SOClass(ObjectOutputStream out) {
        this.out = out;
    }
    
    public abstract void execute(Object object) throws IOException;
    
}
