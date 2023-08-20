/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.Hall;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author HOME
 */
public class HallsTableModel extends AbstractTableModel {

    private List<Hall> halls;

    public HallsTableModel(List<Hall> halls) {
        this.halls = halls;
    }
    
    
    
    @Override
    public int getRowCount() {
        return halls.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Hall hall = halls.get(rowIndex);
        switch(columnIndex){
            case 0:
                return hall.getHallName();
            case 1:
                return hall.getAddress();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        String[] titles = {"Hall name", "Address"};
        return titles[column];
    }

    
    
    public Hall getHall(int rowIndex){
        return halls.get(rowIndex);
    }
    
}
