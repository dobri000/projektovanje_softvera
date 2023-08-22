/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import domain.Admin;
import domain.Country;
import domain.Hall;
import domain.Player;
import domain.PlayerEngagement;
import domain.Roster;
import domain.Season;
import domain.StaffMember;
import domain.StaffMemberEngagement;
import domain.Team;
import enumeration.Hand;
import enumeration.PlayerPosition;
import enumeration.StaffMemberPosition;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HOME
 */
public class DBBroker {

    private static DBBroker instance;
    private List<Admin> loggedAdmins = new ArrayList<>();

    public static DBBroker getInstance() {
        if (instance == null) {
            instance = new DBBroker();
        }
        return instance;
    }

    public void deletePlayer(Player player) throws SQLException {
        String query = "delete from player where playerId = " + player.getPlayerId();
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        Statement stat = null;
        try {
            stat = connection.createStatement();
            stat.executeUpdate(query);

            connection.commit();
            stat.close();
            connection.close();

        } catch (SQLException ex) {
            connection.rollback();
        } finally {
            if (stat != null) {
                stat.close();
            }
            connection.close();
        }

    }

    public void deleteStaffMember(StaffMember staff) throws SQLException {
        String query = "delete from staffMember where staffMemberId = " + staff.getStaffMemberId();
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        Statement stat = null;
        try {
            stat = connection.createStatement();
            stat.executeUpdate(query);

            connection.commit();
            stat.close();
            connection.close();
        } catch (SQLException ex) {
            connection.rollback();
        } finally {
            if (stat != null) {
                stat.close();
            }
            connection.close();
        }
    }

    public String login(Admin admin) throws SQLException {
        if(loggedAdmins.contains(admin)){
            return "Admin allready logged";
        }
        String message = null;
        String username = admin.getUsername();
        String password = admin.getPassword();
        String query = "select * from admin where username = ? and password = ?";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                message = "Correct credentials";
                loggedAdmins.add(admin);
            } else {
                message = "Invalid username or password";
            }

            connection.commit();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            connection.rollback();
        } finally {
            if (ps != null) {
                ps.close();
            }
            connection.close();
        }
        return message;
    }

    public void addPlayer(Player player) throws SQLException {
        String query = "insert into player (firstname, lastname, birthdate, height, weight, spike, block, dominantHand, nationality) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareCall(query);
            ps.setString(1, player.getFirstname());
            ps.setString(2, player.getLastname());
            ps.setDate(3, Date.valueOf(player.getBirthdate()));
            ps.setInt(4, player.getHeight());
            ps.setInt(5, player.getWeight());
            ps.setInt(6, player.getSpike());
            ps.setInt(7, player.getBlock());
            ps.setString(8, String.valueOf(player.getDominantHand()));
            ps.setInt(9, player.getNationality().getCountryId());
            ps.executeUpdate();
            
            connection.commit();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            connection.rollback();
        } finally {
            if (ps != null) {
                ps.close();
            }
            connection.close();
        }
    }

    public void addStaffMember(StaffMember staffMember) throws SQLException {
        String query = "insert into staffMember (firstname, lastname, birthdate, nationality) values (?, ?, ?, ?)";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareCall(query);
            ps.setString(1, staffMember.getFirstname());
            ps.setString(2, staffMember.getLastname());
            ps.setDate(3, Date.valueOf(staffMember.getBirthdate()));
            ps.setInt(4, staffMember.getNationality().getCountryId());
            ps.executeUpdate();
            
            connection.commit();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<Player> getPlayersByFirstnameOrLastname(String search) throws SQLException {
        List<Player> players = new ArrayList<>();
        String query = "SELECT * FROM player p left JOIN country c on(p.nationality = c.countryId) WHERE LOWER(firstname) LIKE '%" + search + "%' OR LOWER(lastname) LIKE '%" + search + "%' OR LOWER(CONCAT(firstname,' ',lastname)) LIKE '%" + search + "%'";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        Statement stat = null;
        try {
            stat = connection.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                int playerId = rs.getInt("playerId");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                LocalDate birth = rs.getDate("birthdate").toLocalDate();
                int height = rs.getInt("height");
                int weight = rs.getInt("weight");
                int spike = rs.getInt("spike");
                int block = rs.getInt("block");
                Hand dominantHand = Hand.valueOf(rs.getString("dominantHand"));
                int countryId = rs.getInt("countryId");
                String countryName = rs.getString("countryName");
                Country nationality = new Country(countryId, countryName);
                players.add(new Player(playerId, firstname, lastname, birth, height, weight, spike, block, dominantHand, nationality));
            }

            connection.commit();
            stat.close();
            connection.close();
        } catch (SQLException ex) {
            connection.rollback();
        } finally {
            if (stat != null) {
                stat.close();
            }
            connection.close();
        }
        return players;
    }

    public List<StaffMember> getStaffMembersByFirstnameOrLastname(String search) throws SQLException {
        List<StaffMember> staffMembers = new ArrayList<>();
        String query = "SELECT * FROM staffMember s LEFT JOIN country c on(s.nationality = c.countryId) WHERE LOWER(firstname) LIKE '%" + search + "%' OR LOWER(lastname) LIKE '%" + search + "%' OR LOWER(CONCAT(firstname,' ',lastname)) LIKE '%" + search + "%'";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        Statement stat = null;
        try {
            stat = connection.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                int staffMemberId = rs.getInt("staffMemberId");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                LocalDate birth = rs.getDate("birthdate").toLocalDate();
                int countryId = rs.getInt("countryId");
                String countryName = rs.getString("countryName");
                Country nationality = new Country(countryId, countryName);
                staffMembers.add(new StaffMember(staffMemberId, firstname, lastname, birth, nationality));
            }

            connection.commit();
            stat.close();
            connection.close();
        } catch (SQLException ex) {
            connection.rollback();
        } finally {
            if (stat != null) {
                stat.close();
            }
            connection.close();
        }
        return staffMembers;
    }

    public List<Country> getAllCountries() throws SQLException {
        List<Country> countries = new ArrayList<>();
        String query = "select * from country";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        Statement stat = null;
        try {
            stat = connection.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                int countryId = rs.getInt("countryId");
                String countryName = rs.getString("countryName");
                Country country = new Country(countryId, countryName);
                countries.add(country);
            }

            connection.commit();
            stat.close();
            connection.close();
        } catch (SQLException ex) {
            connection.rollback();
        } finally {
            if (stat != null) {
                stat.close();
            }
            connection.close();
        }
        return countries;
    }

    public List<Hall> findHallsSearch(String search) throws SQLException {
        List<Hall> halls = new ArrayList<>();
        String query = "select * from hall where lower(hallName) like '%" + search + "%'";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        Statement stat = null;
        try {
            stat = connection.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                int hallId = rs.getInt("hallId");
                String hallName = rs.getString("HallName");
                String address = rs.getString("address");
                halls.add(new Hall(hallId, hallName, address));
            }

            connection.commit();
            stat.close();
            connection.close();
        } catch (SQLException ex) {
            connection.rollback();
        } finally {
            if (stat != null) {
                stat.close();
            }
            connection.close();
        }
        return halls;
    }

    public void addTeam(Team team) throws SQLException {
        String query = "insert into team (teamName, founded, country, hall) values (?, ?, ?, ?)";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, team.getTeamName());
            ps.setInt(2, team.getFounded());
            ps.setInt(3, team.getCountry().getCountryId());
            ps.setInt(4, team.getHall().getHallId());
            ps.executeUpdate();
            
            connection.commit();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            connection.rollback();
        } finally {
            if (ps != null) {
                ps.close();
            }
            connection.close();
        }
    }

    public void updatePlayer(Player player) throws SQLException {
        String query = "update player set firstname = ?, lastname = ?, birthdate = ?, height = ?, weight = ?, spike = ?, block = ?, dominantHand = ?, nationality = ? where playerId = ?";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, player.getFirstname());
            ps.setString(2, player.getLastname());
            ps.setDate(3, Date.valueOf(player.getBirthdate()));
            ps.setInt(4, player.getHeight());
            ps.setInt(5, player.getWeight());
            ps.setInt(6, player.getSpike());
            ps.setInt(7, player.getBlock());
            ps.setString(8, String.valueOf(player.getDominantHand()));
            ps.setInt(9, player.getNationality().getCountryId());
            ps.setInt(10, player.getPlayerId());
            ps.executeUpdate();
            
            connection.commit();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            connection.rollback();
        } finally {
            if (ps != null) {
                ps.close();
            }
            connection.close();
        }
    }

    public void updateTeam(Team team) throws SQLException {
        String query = "update team set teamName = ?, founded = ?, country = ?, hall = ? where teamid = ?";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, team.getTeamName());
            ps.setInt(2, team.getFounded());
            ps.setInt(3, team.getCountry().getCountryId());
            ps.setInt(4, team.getHall().getHallId());
            ps.setInt(5, team.getTeamId());
            ps.executeUpdate();
            
            connection.commit();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            connection.rollback();
        } finally {
            if (ps != null) {
                ps.close();
            }
            connection.close();
        }
    }

    public void updateStaffMember(StaffMember staffMember) throws SQLException {
        String query = "update staffMember set firstname = ?, lastname = ?, birthdate = ?, nationality = ? where staffMemberId = ?";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, staffMember.getFirstname());
            ps.setString(2, staffMember.getLastname());
            ps.setDate(3, Date.valueOf(staffMember.getBirthdate()));
            ps.setInt(4, staffMember.getNationality().getCountryId());
            ps.setInt(5, staffMember.getStaffMemberId());
            ps.executeUpdate();
            
            connection.commit();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            connection.rollback();
        } finally {
            if (ps != null) {
                ps.close();
            }
            connection.close();
        }
    }

    public void addHall(Hall hall) throws SQLException {
        String query = "insert into hall (hallName, address) values (?, ?)";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, hall.getHallName());
            ps.setString(2, hall.getAddress());

            ps.executeUpdate();
            
            connection.commit();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            connection.rollback();
        } finally {
            if (ps != null) {
                ps.close();
            }
            connection.close();
        }
    }

    public List<Team> searchTeam(String search) throws SQLException {
        List<Team> teams = new ArrayList<>();
        String query = "SELECT * FROM team t JOIN country c ON t.country = c.countryId JOIN hall h ON t.hall = h.hallId where lower(t.teamName) like '" + search + "%'";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        Statement stat = null;
        try {
            stat = connection.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                int teamId = rs.getInt("teamId");
                String teamName = rs.getString("teamName");
                int founded = rs.getInt("founded");
                int countryId = rs.getInt("countryId");
                String countryName = rs.getString("countryName");
                Country country = new Country(countryId, countryName);
                int hallId = rs.getInt("hallId");
                String hallName = rs.getString("hallName");
                String address = rs.getString("address");
                Hall hall = new Hall(hallId, hallName, address);
                Team team = new Team(teamId, teamName, founded, country, hall);
                teams.add(team);
            }

            connection.commit();
            stat.close();
            connection.close();
        } catch (SQLException ex) {
            connection.rollback();
        } finally {
            if (stat != null) {
                stat.close();
            }
            connection.close();
        }
        return teams;
    }

    public List<Team> getAllTeams() throws SQLException {
        List<Team> teams = new ArrayList<>();
        String query = "SELECT * FROM team t JOIN country c ON t.country = c.countryId JOIN hall h ON t.hall = h.hallId order by teamName";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        Statement stat = null;
        try {
            stat = connection.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                int teamId = rs.getInt("teamId");
                String teamName = rs.getString("teamName");
                int founded = rs.getInt("founded");
                int countryId = rs.getInt("countryId");
                String countryName = rs.getString("countryName");
                Country country = new Country(countryId, countryName);
                int hallId = rs.getInt("hallId");
                String hallName = rs.getString("hallName");
                String address = rs.getString("address");
                Hall hall = new Hall(hallId, hallName, address);
                Team team = new Team(teamId, teamName, founded, country, hall);
                teams.add(team);
            }

            connection.commit();
            stat.close();
            connection.close();
        } catch (SQLException ex) {
            connection.rollback();
        } finally {
            if (stat != null) {
                stat.close();
            }
            connection.close();
        }
        return teams;
    }

    public List<Player> getAllPlayers() throws SQLException {
        List<Player> players = new ArrayList<>();
        String query = "SELECT * FROM player p left JOIN country c on p.nationality = c.countryId order by firstname";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        Statement stat = null;
        try {
            stat = connection.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                int playerId = rs.getInt("playerId");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                LocalDate birth = rs.getDate("birthdate").toLocalDate();
                int height = rs.getInt("height");
                int weight = rs.getInt("weight");
                int spike = rs.getInt("spike");
                int block = rs.getInt("block");
                Hand dominantHand = Hand.valueOf(rs.getString("dominantHand"));
                int countryId = rs.getInt("countryId");
                String countryName = rs.getString("countryName");
                Country nationality = new Country(countryId, countryName);
                players.add(new Player(playerId, firstname, lastname, birth, height, weight, spike, block, dominantHand, nationality));
            }

            connection.commit();
            stat.close();
            connection.close();
        } catch (SQLException ex) {
            connection.rollback();
        } finally {
            if (stat != null) {
                stat.close();
            }
            connection.close();
        }
        return players;
    }

    public List<StaffMember> getAllStaffMembers() throws SQLException {
        List<StaffMember> staffMembers = new ArrayList<>();
        String query = "SELECT * FROM staffMember s left JOIN country c on s.nationality = c.countryId order by firstname";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        Statement stat = null;
        try {
            stat = connection.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                int staffMemberId = rs.getInt("staffMemberId");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                LocalDate birth = rs.getDate("birthdate").toLocalDate();
                int countryId = rs.getInt("countryId");
                String countryName = rs.getString("countryName");
                Country nationality = new Country(countryId, countryName);
                staffMembers.add(new StaffMember(staffMemberId, firstname, lastname, birth, nationality));
            }

            connection.commit();
            stat.close();
            connection.close();
        } catch (SQLException ex) {
            connection.rollback();
        } finally {
            if (stat != null) {
                stat.close();
            }
            connection.close();
        }
        return staffMembers;
    }

    public void addPlayerEngagement(PlayerEngagement playerEngagement) throws SQLException {
        Roster roster = playerEngagement.getRoster();
        if (rosterExist(roster) == false) {
            addRoster(roster);
        }
        getRosterId(roster);
        String query = "insert into playerEngagement values (?, ?, ?, ?, ?)";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareCall(query);
            ps.setInt(1, playerEngagement.getPlayer().getPlayerId());
            ps.setInt(2, playerEngagement.getRoster().getTeam().getTeamId());
            ps.setInt(3, playerEngagement.getRoster().getRosterId());
            ps.setString(4, String.valueOf(playerEngagement.getPosition()));
            ps.setInt(5, playerEngagement.getNumber());
            ps.executeUpdate();
            
            connection.commit();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            connection.rollback();
            System.out.println(ex.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }
            connection.close();
        }
    }

    public void addStaffMemberEngagement(StaffMemberEngagement engagement) throws SQLException {
        Roster roster = engagement.getRoster();
        if (rosterExist(roster) == false) {
            addRoster(roster);
        }
        getRosterId(roster);
        String query = "insert into staffMemberEngagement values (?, ?, ?, ?)";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, engagement.getStaffMember().getStaffMemberId());
            ps.setInt(2, engagement.getRoster().getTeam().getTeamId());
            ps.setInt(3, engagement.getRoster().getRosterId());
            ps.setString(4, String.valueOf(engagement.getPosition()));
            ps.executeUpdate();
            
            connection.commit();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            connection.rollback();
        } finally {
            if (ps != null) {
                ps.close();
            }
            connection.close();
        }
    }

    public boolean rosterExist(Roster roster) throws SQLException {
        boolean found = false;
        String query = "select * from roster where teamId = ? and seasonId = ?";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, roster.getTeam().getTeamId());
            ps.setInt(2, roster.getSeason().getSeasonId());
            ResultSet rs = ps.executeQuery();
            found = rs.next();
            
            connection.commit();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            connection.rollback();
        } finally {
            if (ps != null) {
                ps.close();
            }
            connection.close();
        }
        return found;
    }

    public void addRoster(Roster roster) throws SQLException {
        String query = "insert into roster (teamId, seasonId) values (?, ?)";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, roster.getTeam().getTeamId());
            ps.setInt(2, roster.getSeason().getSeasonId());
            ps.executeUpdate();
            
            connection.commit();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            connection.rollback();
        } finally {
            if (ps != null) {
                ps.close();
            }
            connection.close();
        }
    }

    public void getRosterId(Roster roster) throws SQLException {
        String query = "select * from roster where teamId = ? and seasonId = ?";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareCall(query);
            ps.setInt(1, roster.getTeam().getTeamId());
            ps.setInt(2, roster.getSeason().getSeasonId());
            ResultSet rs = ps.executeQuery();
            rs.next();
            roster.setRosterId(rs.getInt("rosterId"));
            
            connection.commit();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            connection.rollback();
        } finally {
            if (ps != null) {
                ps.close();
            }
            connection.close();
        }
    }

    public List<Season> getAllSeasons() throws SQLException {
        List<Season> seasons = new ArrayList<>();
        String query = "select * from season order by startYear asc";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        Statement stat = null;
        try {
            stat = connection.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                int seasonId = rs.getInt("seasonId");
                int startYear = rs.getInt("startYear");
                int endYear = rs.getInt("endYear");
                Season season = new Season(seasonId, startYear, endYear);
                seasons.add(season);
            }
            connection.commit();
            stat.close();
            connection.close();
        } catch (SQLException ex) {
            connection.rollback();
        } finally {
            if (stat != null) {
                stat.close();
            }
            connection.close();
        }
        return seasons;
    }

    public List<PlayerEngagement> getPlayerEngagements(Player player) throws SQLException {
        List<PlayerEngagement> engagements = new ArrayList<>();
        String query = "SELECT * FROM playerengagement pe LEFT JOIN roster r ON (pe.teamId = r.teamId AND pe.rosterId = r.rosterId) LEFT JOIN team t ON r.teamId = t.teamId LEFT JOIN season s ON r.seasonId = s.seasonId LEFT JOIN country c ON t.country = c.countryId LEFT JOIN hall h ON t.hall = h.hallId WHERE pe.playerId = ? ORDER BY s.startYear ASC;";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, player.getPlayerId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int hallId = rs.getInt("hallId");
                String hallName = rs.getString("hallName");
                String address = rs.getString("address");
                Hall hall = new Hall(hallId, hallName, address);
                int countryId = rs.getInt("countryId");
                String countryName = rs.getString("countryName");
                Country country = new Country(countryId, hallName);
                int teamId = rs.getInt("teamId");
                String teamName = rs.getString("teamName");
                int founded = rs.getInt("founded");
                Team team = new Team(teamId, teamName, founded, country, hall);
                int seasonId = rs.getInt("seasonId");
                int startYear = rs.getInt("startYear");
                int endYear = rs.getInt("endYear");
                Season season = new Season(seasonId, startYear, endYear);
                int rosterId = rs.getInt("rosterId");
                Roster roster = new Roster(team, rosterId, season);
                PlayerPosition position = PlayerPosition.valueOf(rs.getString("position"));
                int number = rs.getInt("number");
                PlayerEngagement engagement = new PlayerEngagement(player, roster, position, number);
                engagements.add(engagement);

            }
            
            connection.commit();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            connection.rollback();
        } finally {
            if (ps != null) {
                ps.close();
            }
            connection.close();
        }
        return engagements;
    }

    public List<StaffMemberEngagement> getStaffMemberEngagements(StaffMember staffMember) throws SQLException {
        List<StaffMemberEngagement> engagements = new ArrayList<>();
        String query = "SELECT * FROM staffMemberEngagement pe LEFT JOIN roster r ON (pe.teamId = r.teamId AND pe.rosterId = r.rosterId) LEFT JOIN team t ON r.teamId = t.teamId LEFT JOIN season s ON r.seasonId = s.seasonId LEFT JOIN country c ON t.country = c.countryId LEFT JOIN hall h ON t.hall = h.hallId WHERE pe.staffMemberId = ? ORDER BY s.startYear ASC;";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, staffMember.getStaffMemberId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int hallId = rs.getInt("hallId");
                String hallName = rs.getString("hallName");
                String address = rs.getString("address");
                Hall hall = new Hall(hallId, hallName, address);
                int countryId = rs.getInt("countryId");
                String countryName = rs.getString("countryName");
                Country country = new Country(countryId, hallName);
                int teamId = rs.getInt("teamId");
                String teamName = rs.getString("teamName");
                int founded = rs.getInt("founded");
                Team team = new Team(teamId, teamName, founded, country, hall);
                int seasonId = rs.getInt("seasonId");
                int startYear = rs.getInt("startYear");
                int endYear = rs.getInt("endYear");
                Season season = new Season(seasonId, startYear, endYear);
                int rosterId = rs.getInt("rosterId");
                Roster roster = new Roster(team, rosterId, season);
                StaffMemberPosition position = StaffMemberPosition.valueOf(rs.getString("position"));
                StaffMemberEngagement engagement = new StaffMemberEngagement(staffMember, roster, position);
                engagements.add(engagement);

            }
            
            connection.commit();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            connection.rollback();
        } finally {
            if (ps != null) {
                ps.close();
            }
            connection.close();
        }
        return engagements;
    }
    
    public void deleteTeam(Team team) throws SQLException  {
        String query = "delete from team where teamId = ?";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, team.getTeamId());
            ps.executeUpdate();
            
            connection.commit();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            connection.rollback();
        } finally {
            if (ps != null) {
                ps.close();
            }
            connection.close();
        }
    }

    public void deletePlayerEngagement(PlayerEngagement engagement) throws SQLException {
        String query = "delete from playerEngagement where playerId = ? and teamId = ? and rosterId = ?";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, engagement.getPlayer().getPlayerId());
            ps.setInt(2, engagement.getRoster().getTeam().getTeamId());
            ps.setInt(3, engagement.getRoster().getRosterId());
            ps.executeUpdate();
            
            connection.commit();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            connection.rollback();
        } finally {
            if (ps != null) {
                ps.close();
            }
            connection.close();
        }
    }

    public List<PlayerEngagement> getPlayerEngagementsOfTeam(Team team) throws SQLException {
        List<PlayerEngagement> engagements = new ArrayList<>();
        String query = "SELECT * FROM playerengagement pe LEFT JOIN roster r ON (pe.teamId = r.teamId AND pe.rosterId = r.rosterId) LEFT JOIN team t ON r.teamId = t.teamId LEFT JOIN season s ON r.seasonId = s.seasonId left join player p on pe.playerId = p.playerId left join country c on p.nationality = c.countryId WHERE pe.teamId = ?";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, team.getTeamId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int countryId = rs.getInt("countryId");
                String countryName = rs.getString("countryName");
                Country country = new Country(countryId, countryName);
                int playerId = rs.getInt("playerId");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                LocalDate birth = rs.getDate("birthdate").toLocalDate();
                int height = rs.getInt("height");
                int weight = rs.getInt("weight");
                int spike = rs.getInt("spike");
                int block = rs.getInt("block");
                Hand dominantHand = Hand.valueOf(rs.getString("dominantHand"));
                Player player = new Player(playerId, firstname, lastname, birth, height, weight, spike, block, dominantHand, country);
                int seasonId = rs.getInt("seasonId");
                int startYear = rs.getInt("startYear");
                int endYear = rs.getInt("endYear");
                Season season = new Season(seasonId, startYear, endYear);
                int rosterId = rs.getInt("rosterId");
                Roster roster = new Roster(team, rosterId, season);
                PlayerPosition position = PlayerPosition.valueOf(rs.getString("position"));
                int number = rs.getInt("number");
                PlayerEngagement engagement = new PlayerEngagement(player, roster, position, number);
                engagements.add(engagement);

            }
            
            connection.commit();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            connection.rollback();
        } finally {
            if (ps != null) {
                ps.close();
            }
            connection.close();
        }
        return engagements;
    }

    public List<StaffMemberEngagement> getStaffMemberEngagementsOfTeam(Team team) throws SQLException {
        List<StaffMemberEngagement> engagements = new ArrayList<>();
        String query = "SELECT * FROM staffmemberengagement pe LEFT JOIN roster r ON (pe.teamId = r.teamId AND pe.rosterId = r.rosterId) LEFT JOIN team t ON r.teamId = t.teamId LEFT JOIN season s ON r.seasonId = s.seasonId left join staffmember p on pe.staffmemberId = p.staffmemberId left join country c on p.nationality = c.countryId WHERE pe.teamId = ?";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, team.getTeamId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int countryId = rs.getInt("countryId");
                String countryName = rs.getString("countryName");
                Country country = new Country(countryId, countryName);
                int staffmemberId = rs.getInt("staffmemberId");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                LocalDate birth = rs.getDate("birthdate").toLocalDate();
                StaffMember staffMember = new StaffMember(staffmemberId, firstname, lastname, birth, country);
                int seasonId = rs.getInt("seasonId");
                int startYear = rs.getInt("startYear");
                int endYear = rs.getInt("endYear");
                Season season = new Season(seasonId, startYear, endYear);
                int rosterId = rs.getInt("rosterId");
                Roster roster = new Roster(team, rosterId, season);
                StaffMemberPosition position = StaffMemberPosition.valueOf(rs.getString("position"));
                StaffMemberEngagement engagement = new StaffMemberEngagement(staffMember, roster, position);
                engagements.add(engagement);

            }
            
            connection.commit();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            connection.rollback();
        } finally {
            if (ps != null) {
                ps.close();
            }
            connection.close();
        }
        return engagements;
    }

    public void deleteStaffMemberEngagement(StaffMemberEngagement engagement) throws SQLException {
        String query = "delete from staffMemberEngagement where staffMemberId = ? and teamId = ? and rosterId = ?";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, engagement.getStaffMember().getStaffMemberId());
            ps.setInt(2, engagement.getRoster().getTeam().getTeamId());
            ps.setInt(3, engagement.getRoster().getRosterId());
            ps.executeUpdate();
            
            connection.commit();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            connection.rollback();
        } finally {
            if (ps != null) {
                ps.close();
            }
            connection.close();
        }
    }

    public List<Hall> getAllHalls() throws SQLException {
        List<Hall> halls = new ArrayList<>();
        String query = "select * from hall";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        Statement stat = null;
        try {
            stat = connection.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                int hallId = rs.getInt("hallId");
                String hallName = rs.getString("hallName");
                String address = rs.getString("address");
                Hall hall = new Hall(hallId, hallName, address);
                halls.add(hall);
            }

            connection.commit();
            stat.close();
            connection.close();
        } catch (SQLException ex) {
            connection.rollback();
        } finally {
            if (stat != null) {
                stat.close();
            }
            connection.close();
        }
        return halls;
    }

    public boolean logout(Admin admin) {
        loggedAdmins.remove(admin);
        return true;
    }
    
}
