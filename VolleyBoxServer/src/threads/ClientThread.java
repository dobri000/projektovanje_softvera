/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import so.impl.SOUpdateStaffMember;
import so.impl.SOGetAllPlayers;
import so.impl.SOAddHall;
import so.impl.SOLogin;
import so.impl.SOGetStaffMemberEngagements;
import so.impl.SOAddTeam;
import so.impl.SOGetStaffMemberEngagementsOfTeam;
import so.impl.SOUpdatePlayer;
import so.impl.SOSearchTeams;
import so.impl.SOAddPlayer;
import so.impl.SOGetPlayerEngagementsOfTeam;
import so.impl.SOGetAllCountries;
import so.impl.SOGetPlayerEngagements;
import so.impl.SOGetAllSeasons;
import so.impl.SODeleteStaffMember;
import so.impl.SOAddPlayerEngagement;
import so.impl.SOUpdateTeam;
import so.impl.SODeleteStaffMemberEngagement;
import so.impl.SOSearchHalls;
import so.impl.SODeletePlayerEngagement;
import so.impl.SOAddStaffMemberEngagement;
import so.impl.SOAddStaffMember;
import so.impl.SOGetAllTeams;
import so.impl.SODeletePlayer;
import so.impl.SOGetAllHalls;
import so.impl.SOSearchStaffMembers;
import so.impl.SOSearchPlayers;
import so.impl.SOGetAllStaffMembers;
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
import transfer.Response;

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
                Operation operation = request.getOperation();
                SOInterface so = null;
                switch (operation) {
                    case LOGIN:
                        so = new SOLogin();
                        break;
                    case GET_ALL_COUNTRIES:
                        so = new SOGetAllCountries();
                        break;
                    case ADD_PLAYER:
                        so = new SOAddPlayer();
                        break;
                    case ADD_TEAM:
                        so = new SOAddTeam();
                        break;
                    case SEARCH_HALLS:
                        so = new SOSearchHalls();
                        break;
                    case SEARCH_PLAYERS:
                        so = new SOSearchPlayers();
                        break;
                    case UPDATE_PLAYER:
                        so = new SOUpdatePlayer();
                        break;
                    case ADD_HALL:
                        so = new SOAddHall();
                        break;
                    case SEARCH_TEAMS:
                        so = new SOSearchTeams();
                        break;
                    case GET_ALL_PLAYERS:
                        so = new SOGetAllPlayers();
                        break;
                    case GET_ALL_TEAMS:
                        so = new SOGetAllTeams();
                        break;
                    case ADD_PLAYER_ENGAGEMENT:
                        so = new SOAddPlayerEngagement();
                        break;
                    case GET_ALL_SEASONS:
                        so = new SOGetAllSeasons();
                        break;
                    case GET_PLAYER_ENGAGEMENTS:
                        so = new SOGetPlayerEngagements();
                        break;
                    case DELETE_PLAYER_ENGAGEMENT:
                        so = new SODeletePlayerEngagement();
                        break;
                    case GET_PLAYER_ENGAGEMENTS_OF_TEAM:
                        so = new SOGetPlayerEngagementsOfTeam();
                        break;
                    case ADD_STAFF_MEMBER:
                        so = new SOAddStaffMember();
                        break;
                    case UPDATE_STAFF_MEMBER:
                        so = new SOUpdateStaffMember();
                        break;
                    case DELETE_STAFF_MEMBER_ENGAGEMENT:
                        so = new SODeleteStaffMemberEngagement();
                        break;
                    case GET_STAFF_MEMBER_ENGAGEMENTS:
                        so = new SOGetStaffMemberEngagements();
                        break;
                    case ADD_STAFF_MEMBER_ENGAGEMENT:
                        so = new SOAddStaffMemberEngagement();
                        break;
                    case GET_ALL_STAFF_MEMBERS:
                        so = new SOGetAllStaffMembers();
                        break;
                    case SEARCH_STAFF_MEMBERS:
                        so = new SOSearchStaffMembers();
                        break;
                    case GET_STAFF_MEMBER_ENGAGEMENTS_OF_TEAM:
                        so = new SOGetStaffMemberEngagementsOfTeam();
                        break;
                    case GET_ALL_HALLS:
                        so = new SOGetAllHalls();
                        break;
                    case DELETE_PLAYER:
                        so = new SODeletePlayer();
                        break;
                    case DELETE_STAFF_MEMBER:
                        so = new SODeleteStaffMember();
                        break;
                    case UPDATE_TEAM:
                        so = new SOUpdateTeam();
                        break;
                    case QUIT:
                        status = false;
                        terminate();
                        break;
                }
                if(status){
                    Response response = so.execute(request.getObject());
                    out.writeObject(response);
                }
            } catch (IOException ex) {
                status = false;
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
            in.close();
            out.close();
            socket.close();
            Server.removeClient(this);
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
