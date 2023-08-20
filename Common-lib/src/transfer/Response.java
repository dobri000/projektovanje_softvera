/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import enumeration.ServerResponse;
import java.io.Serializable;

/**
 *
 * @author HOME
 */
public class Response implements Serializable{
    private ServerResponse serverResponse;
    private Object object;

    public Response(ServerResponse serverResponse, Object object) {
        this.serverResponse = serverResponse;
        this.object = object;
    }

    public ServerResponse getServerResponse() {
        return serverResponse;
    }

    public void setServerResponse(ServerResponse serverResponse) {
        this.serverResponse = serverResponse;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
    
    
}
