/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import enumeration.Operation;
import java.io.Serializable;

/**
 *
 * @author HOME
 */
public class Request implements Serializable {
    
    private Operation operation;
    private Object object;

    public Request(Operation operation, Object object) {
        this.operation = operation;
        this.object = object;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
    
    

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
    
    
    
}
