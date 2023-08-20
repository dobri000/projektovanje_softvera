/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.Season;
import domain.StaffMemberEngagement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author HOME
 */
public class StaffEngagementsTeamModel extends AbstractTableModel {

    private List<StaffMemberEngagement> engagements;
    private List<StaffMemberEngagement> showedEngagements;

    public StaffEngagementsTeamModel(List<StaffMemberEngagement> engagements) {
        this.engagements = engagements;
        showedEngagements = new ArrayList<>();
    }
    
    public void showSeason(Season season){
        showedEngagements = new ArrayList<>();
        for(StaffMemberEngagement engagement : engagements){
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
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StaffMemberEngagement engagement = showedEngagements.get(rowIndex);
        switch(columnIndex){
            case 0:
                return engagement.getStaffMember().getFirstname();
            case 1:
                return engagement.getStaffMember().getLastname();
            case 2:
                return engagement.getPosition();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        String[] titles = {"Firstname", "Lastname", "Position"};
        return titles[column];
    }
    
    public StaffMemberEngagement getEngagement(int row){
        StaffMemberEngagement engagement = showedEngagements.remove(row);
        engagements.remove(engagement);
        fireTableDataChanged();
        return engagement;
    }
    
}
