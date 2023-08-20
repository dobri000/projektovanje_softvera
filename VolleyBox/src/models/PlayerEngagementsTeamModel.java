/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.PlayerEngagement;
import domain.Season;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author HOME
 */
public class PlayerEngagementsTeamModel extends AbstractTableModel {

    private List<PlayerEngagement> engagements;
    private List<PlayerEngagement> showedEngagements;

    public PlayerEngagementsTeamModel(List<PlayerEngagement> engagements) {
        this.engagements = engagements;
        showedEngagements = new ArrayList<>();
    }
    
    public void showSeason(Season season){
        showedEngagements = new ArrayList<>();
        for(PlayerEngagement engagement : engagements){
            if(engagement.getRoster().getSeason().equals(season)){
                showedEngagements.add(engagement);
            }
        }
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return showedEngagements.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PlayerEngagement engagement = showedEngagements.get(rowIndex);
        switch(columnIndex){
            case 0:
                return engagement.getNumber();
            case 1:
                return engagement.getPlayer().getFirstname();
            case 2:
                return engagement.getPlayer().getLastname();
            case 3:
                return engagement.getPosition();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        String[] titles = {"Number", "Firstname", "Lastname", "Position"};
        return titles[column];
    }
    
    public PlayerEngagement getEngagement(int row){
        PlayerEngagement engagement = showedEngagements.remove(row);
        engagements.remove(engagement);
        fireTableDataChanged();
        return engagement;
    }
    
    
}
