/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import enumeration.Operation;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.Server;
import so.*;
import transfer.Request;

/**
 *
 * @author HOME
 */
public class ClientThread extends Thread {

    private Socket socket;
    private boolean status = true;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ClientThread(Socket socket) throws IOException {
        this.socket = socket;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        while (status) {
            try {
                Request request = (Request) in.readObject();
                Operation so = request.getOperation();
                switch (so) {
                    case LOGIN:
                        new SOLogin(out).execute(request.getObject());
                        break;
                    case GET_ALL_COUNTRIES:
                        new SOGetAllCountries(out).execute(request.getObject());
                        break;
                    case ADD_PLAYER:
                        new SOAddPlayer(out).execute(request.getObject());
                        break;
                    case ADD_TEAM:
                        new SOAddTeam(out).execute(request.getObject());
                        break;
                    case SEARCH_HALLS:
                        new SOSearchHalls(out).execute(request.getObject());
                        break;
                    case SEARCH_PLAYERS:
                        new SOSearchPlayers(out).execute(request.getObject());
                        break;
                    case UPDATE_PLAYER:
                        new SOUpdatePlayer(out).execute(request.getObject());
                        break;
                    case ADD_HALL:
                        new SOAddHall(out).execute(request.getObject());
                        break;
                    case SEARCH_TEAMS:
                        new SOSearchTeams(out).execute(request.getObject());
                        break;
                    case GET_ALL_PLAYERS:
                        new SOGetAllPlayers(out).execute(request.getObject());
                        break;
                    case GET_ALL_TEAMS:
                        new SOGetAllTeams(out).execute(request.getObject());
                        break;
                    case ADD_PLAYER_ENGAGEMENT:
                        new SOAddPlayerEngagement(out).execute(request.getObject());
                        break;
                    case GET_ALL_SEASONS:
                        new SOGetAllSeasons(out).execute(request.getObject());
                        break;
                    case GET_PLAYER_ENGAGEMENTS:
                        new SOGetPlayerEngagements(out).execute(request.getObject());
                        break;
                    case DELETE_PLAYER_ENGAGEMENT:
                        new SODeletePlayerEngagement(out).execute(request.getObject());
                        break;
                    case GET_PLAYER_ENGAGEMENTS_OF_TEAM:
                        new SOGetPlayerEngagementsOfTeam(out).execute(request.getObject());
                        break;
                    case ADD_STAFF_MEMBER:
                        new SOAddStaffMember(out).execute(request.getObject());
                        break;
                    case UPDATE_STAFF_MEMBER:
                        new SOUpdateStaffMember(out).execute(request.getObject());
                        break;
                    case DELETE_STAFF_MEMBER_ENGAGEMENT:
                        new SODeleteStaffMemberEngagement(out).execute(request.getObject());
                        break;
                    case GET_STAFF_MEMBER_ENGAGEMENTS:
                        new SOGetStaffMemberEngagements(out).execute(request.getObject());
                        break;
                    case ADD_STAFF_MEMBER_ENGAGEMENT:
                        new SOAddStaffMemberEngagement(out).execute(request.getObject());
                        break;
                    case GET_ALL_STAFF_MEMBERS:
                        new SOGetAllStaffMembers(out).execute(request.getObject());
                        break;
                    case SEARCH_STAFF_MEMBERS:
                        new SOSearchStaffMembers(out).execute(request.getObject());
                        break;
                    case GET_STAFF_MEMBER_ENGAGEMENTS_OF_TEAM:
                        new SOGetStaffMemberEngagementsOfTeam(out).execute(request.getObject());
                        break;
                    case GET_ALL_HALLS:
                        new SOGetAllHalls(out).execute(request.getObject());
                        break;
                    case DELETE_PLAYER:
                        new SODeletePlayer(out).execute(request.getObject());
                        break;
                    case DELETE_STAFF_MEMBER:
                        new SODeleteStaffMember(out).execute(request.getObject());
                        break;
                    case UPDATE_TEAM:
                        new SOUpdateTeam(out).execute(request.getObject());
                        break;
                    case QUIT:
                        terminate();
                        break;
                }
            } catch (IOException ex) {
                terminate();
                interrupt();
                Server.removeClient(this);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void terminate() {
        try {
            status = false;
            in.close();
            out.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
