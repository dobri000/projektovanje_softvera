/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import domain.Admin;
import domain.Country;
import domain.Hall;
import domain.Player;
import domain.PlayerEngagement;
import domain.Season;
import domain.StaffMember;
import domain.StaffMemberEngagement;
import domain.Team;
import enumeration.Operation;
import enumeration.ServerResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import session.Session;
import transfer.Request;
import transfer.Response;

/**
 *
 * @author HOME
 */
public class Communication {

    private static Communication instance;

    private Socket socket;
    private static ObjectInputStream in;
    private static ObjectOutputStream out;

    private Communication() {
        try {
            socket = new Socket("localhost", 6666);
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Connection error");
                System.exit(0);
        }
    }

    public static Communication getInstance() {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;
    }

    public void sendRequest(Operation operation, Object object) {
        try {
            out.writeObject(new Request(operation, object));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Connection error");
                System.exit(0);
        }
    }

    public Response getResponse() {
        Response response = null;
        try {
            response = (Response) in.readObject();
            if(response.getServerResponse() == ServerResponse.ERROR) {
                JOptionPane.showMessageDialog(null, (String) response.getObject());
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Connection error");
                System.exit(0);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    public boolean login(String username, String password) {
        Admin admin = new Admin(username, password);
        sendRequest(Operation.LOGIN, admin);
        Response response = getResponse();
        if(response.getServerResponse().equals(ServerResponse.ERROR))
            return false;
        return true;
    }

    public void deletePlayer(Player player) {
        sendRequest(Operation.DELETE_PLAYER, player);
        Response response = getResponse();
        if(response.getServerResponse().equals(ServerResponse.OK))
            JOptionPane.showMessageDialog(null, "Player successfully deleted");
    }
    
    public void deleteTeam(Team team) {
        sendRequest(Operation.DELETE_TEAM, team);
        Response response = getResponse();
        if(response.getServerResponse().equals(ServerResponse.OK))
            JOptionPane.showMessageDialog(null, "Team successfully deleted");
    }

    public void deleteStaffMember(StaffMember staff) {
        sendRequest(Operation.DELETE_STAFF_MEMBER, staff);
        Response response = getResponse();
        if(response.getServerResponse().equals(ServerResponse.OK))
            JOptionPane.showMessageDialog(null, "Staff member successfully deleted");
    }

    public List<Country> getAllCountries() {
        sendRequest(Operation.GET_ALL_COUNTRIES, null);
        Response response = getResponse();
        if(response.getServerResponse().equals(ServerResponse.OK))
            return (List<Country>) response.getObject();
        return new ArrayList<>();
    }

    public void addPlayer(Player player) {
        sendRequest(Operation.ADD_PLAYER, player);
        Response response = getResponse();
        if(response.getServerResponse().equals(ServerResponse.OK))
            JOptionPane.showMessageDialog(null, "Player successfully added");
    }

    public void addTeam(Team team) {
        sendRequest(Operation.ADD_TEAM, team);
        Response response = getResponse();
        if(response.getServerResponse().equals(ServerResponse.OK))
            JOptionPane.showMessageDialog(null, "Team successfully added");
    }

    public List<Hall> searchHalls(String search) {
        sendRequest(Operation.SEARCH_HALLS, search);
        Response response = getResponse();
        if(response.getServerResponse().equals(ServerResponse.OK))
            return (List<Hall>) response.getObject();
        return new ArrayList<>();
    }

    public List<Player> searchPlayers(String search) {
        sendRequest(Operation.SEARCH_PLAYERS, search);
        Response response = getResponse();
        if(response.getServerResponse().equals(ServerResponse.OK))
            return (List<Player>) response.getObject();
        return new ArrayList<>();
    }

    public List<StaffMember> searchStaffMembers(String search) {
        sendRequest(Operation.SEARCH_STAFF_MEMBERS, search);
        Response response = getResponse();
        if(response.getServerResponse().equals(ServerResponse.OK))
            return (List<StaffMember>) response.getObject();
        return new ArrayList<>();
    }

    public void updatePlayer(Player player) {
        sendRequest(Operation.UPDATE_PLAYER, player);
        Response response = getResponse();
        if(response.getServerResponse().equals(ServerResponse.OK));
            JOptionPane.showMessageDialog(null, "Player successfully updated");
    }

    public void addHall(Hall hall) {
        sendRequest(Operation.ADD_HALL, hall);
        Response response = getResponse();
        if(response.getServerResponse().equals(ServerResponse.OK));
            JOptionPane.showMessageDialog(null, "Hall successfully added");
    }

    public void quit() {
        sendRequest(Operation.QUIT, Session.getInstance().getAdmin());
        closeConnection();
    }

    public List<Team> searchTeam(String search) {
        sendRequest(Operation.SEARCH_TEAMS, search);
        Response response = getResponse();
        if(response.getServerResponse().equals(ServerResponse.OK))
            return (List<Team>) response.getObject();
        return new ArrayList<>();
    }

    public List<Team> getAllTeams() {
        sendRequest(Operation.GET_ALL_TEAMS, null);
        Response response = getResponse();
        if(response.getServerResponse().equals(ServerResponse.OK))
            return (List<Team>) response.getObject();
        return new ArrayList<>();
    }

    private void closeConnection() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Connection error");
            System.exit(0);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Connection error");
            System.exit(0);
        }

    }

    public List<Player> getAllPlayers() {
        sendRequest(Operation.GET_ALL_PLAYERS, null);
        Response response = getResponse();
        if(response.getServerResponse().equals(ServerResponse.OK))
            return (List<Player>) response.getObject();
        return new ArrayList<>();
    }

    public List<StaffMember> getAllStaffMembers() {
        sendRequest(Operation.GET_ALL_STAFF_MEMBERS, null);
        Response response = getResponse();
        if(response.getServerResponse().equals(ServerResponse.OK))
            return (List<StaffMember>) response.getObject();
        return new ArrayList<>();
    }

    public void addPlayerEngagement(PlayerEngagement playerEngagement) {
        sendRequest(Operation.ADD_PLAYER_ENGAGEMENT, playerEngagement);
        Response response = getResponse();
        if(response.getServerResponse().equals(ServerResponse.OK))
            JOptionPane.showMessageDialog(null, "Player engagement successfully added");
    }

    public void addStaffMemberEngagement(StaffMemberEngagement engagement) {
        sendRequest(Operation.ADD_STAFF_MEMBER_ENGAGEMENT, engagement);
        Response response = getResponse();
        if(response.getServerResponse().equals(ServerResponse.OK))
            JOptionPane.showMessageDialog(null, "Staff member engagement successfully added");
    }

    public List<Season> getAllSeasons() {
        sendRequest(Operation.GET_ALL_SEASONS, null);
        Response response = getResponse();
        if(response.getServerResponse().equals(ServerResponse.OK))
            return (List<Season>) response.getObject();
        return new ArrayList<>();
    }

    public List<PlayerEngagement> getPlayerEngagements(Player player) {
        sendRequest(Operation.GET_PLAYER_ENGAGEMENTS, player);
        Response response = getResponse();
        if(response.getServerResponse().equals(ServerResponse.OK))
            return (List<PlayerEngagement>) response.getObject();
        return new ArrayList<>();
    }

    public void deletePlayerEngagement(PlayerEngagement engagement) {
        sendRequest(Operation.DELETE_PLAYER_ENGAGEMENT, engagement);
        Response response = getResponse();
        if(response.getServerResponse().equals(ServerResponse.OK))
            JOptionPane.showMessageDialog(null, "Player engagement successfully deleted");
    }

    public void updateTeam(Team team) {
        sendRequest(Operation.UPDATE_TEAM, team);
        Response response = getResponse();
        if(response.getServerResponse().equals(ServerResponse.OK))
            JOptionPane.showMessageDialog(null, "Team successfully updated");
    }

    public List<PlayerEngagement> getPlayerEngagementsOfTeam(Team team) {
        sendRequest(Operation.GET_PLAYER_ENGAGEMENTS_OF_TEAM, team);
        Response response = getResponse();
        if(response.getServerResponse().equals(ServerResponse.OK))
            return (List<PlayerEngagement>) response.getObject();
        return new ArrayList<>();
    }

    public List<StaffMemberEngagement> getStaffMemberEngagementsOfTeam(Team team) {
        sendRequest(Operation.GET_STAFF_MEMBER_ENGAGEMENTS_OF_TEAM, team);
        Response response = getResponse();
        if(response.getServerResponse().equals(ServerResponse.OK))
            return (List<StaffMemberEngagement>) response.getObject();
        return new ArrayList<>();
    }

    public void addStaffMember(StaffMember staffMember) {
        sendRequest(Operation.ADD_STAFF_MEMBER, staffMember);
        Response response = getResponse();
        if(response.getServerResponse().equals(ServerResponse.OK))
            JOptionPane.showMessageDialog(null, "Staff member successfully added");
    }

    public void updateStaffMember(StaffMember staffMember) {
        sendRequest(Operation.UPDATE_STAFF_MEMBER, staffMember);
        Response response = getResponse();
        if(response.getServerResponse().equals(ServerResponse.OK))
            JOptionPane.showMessageDialog(null, "Staff member successfully updated");
    }

    public void deleteStaffMemberEngagement(StaffMemberEngagement engagement) {
        sendRequest(Operation.DELETE_STAFF_MEMBER_ENGAGEMENT, engagement);
        Response response = getResponse();
        if(response.getServerResponse().equals(ServerResponse.OK))
            JOptionPane.showMessageDialog(null, "Staff member engagement successfully deleted");
    }

    public List<StaffMemberEngagement> getStaffMemberEngagements(StaffMember staffMember) {
        sendRequest(Operation.GET_STAFF_MEMBER_ENGAGEMENTS, staffMember);
        Response response = getResponse();
        if(response.getServerResponse().equals(ServerResponse.OK))
            return (List<StaffMemberEngagement>) response.getObject();
        return new ArrayList<>();
    }

    public List<Hall> getAllHalls() {
        sendRequest(Operation.GET_ALL_HALLS, null);
        Response response = getResponse();
        if(response.getServerResponse().equals(ServerResponse.OK))
            return (List<Hall>) response.getObject();
        return null;
    }

    public boolean logout(Admin admin) {
        sendRequest(Operation.LOGOUT, admin);
        Response response = getResponse();
        if(response.getServerResponse().equals(ServerResponse.OK)){
            JOptionPane.showMessageDialog(null, (String) response.getObject());
            Session.getInstance().setAdmin(null);
            return true;
        } else {
            return true;
        }
    }

}
