/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.Player;
import domain.StaffMember;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author HOME
 */
public class StaffMembersTableModel extends AbstractTableModel {

    private List<StaffMember> staffMembers;

    public StaffMembersTableModel(List<StaffMember> staffMembers) {
        this.staffMembers = staffMembers;
    }
    
    @Override
    public int getRowCount() {
        return staffMembers.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StaffMember staffMember = staffMembers.get(rowIndex);
        switch(columnIndex){
            case 0:
                return staffMember.getFirstname();
            case 1:
                return staffMember.getLastname();
            case 2:
                return staffMember.getBirthdate().format(DateTimeFormatter.ISO_DATE);
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        String[] titles = {"Firstname", "Lastname", "Date of birth"};
        return titles[column];
    }
    
    public StaffMember getStaffMember(int row){
        return staffMembers.get(row);
    }
    
    
    
}
