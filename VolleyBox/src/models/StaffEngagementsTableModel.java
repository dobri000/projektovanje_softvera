/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.StaffMemberEngagement;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author HOME
 */
public class StaffEngagementsTableModel extends AbstractTableModel {

    private List<StaffMemberEngagement> engagements;

    public StaffEngagementsTableModel(List<StaffMemberEngagement> engagements) {
        this.engagements = engagements;
    }
    
    
    
    @Override
    public int getRowCount() {
        return engagements.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StaffMemberEngagement engagement = engagements.get(rowIndex);
        switch(columnIndex){
            case 0:
                return engagement.getRoster().getSeason().toString();
            case 1:
                return engagement.getRoster().getTeam().getTeamName();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int column) {
        String[] titles = {"Season", "Team"};
        return titles[column];
    }
    
    public void removeEngagement(int row){
        engagements.remove(row);
        fireTableDataChanged();
    }
    
    public StaffMemberEngagement getEngagement(int row){
        return engagements.get(row);
    }
    
}
