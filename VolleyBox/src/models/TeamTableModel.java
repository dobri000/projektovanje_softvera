/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.Team;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author HOME
 */
public class TeamTableModel extends AbstractTableModel {

    private List<Team> teams;

    public TeamTableModel(List<Team> teams) {
        this.teams = teams;
    }
    
    
    
    @Override
    public int getRowCount() {
        return teams.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Team team = teams.get(rowIndex);
        switch(columnIndex){
            case 0:
                return team.getTeamName();
            case 1:
                return team.getFounded();
            case 2:
                return team.getCountry().getCountryName();
            case 3:
                return team.getHall().getHallName();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        String[] titles = {"Team name", "Founded", "Country", "Hall"};
        return titles[column];
    }
    
    public Team getTeam(int row){
        return teams.get(row);
    }
    
}
