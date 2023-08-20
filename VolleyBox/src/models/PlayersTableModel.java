/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.Player;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author HOME
 */
public class PlayersTableModel extends AbstractTableModel {

    private List<Player> players;

    public PlayersTableModel(List<Player> players) {
        this.players = players;
    }
    
    @Override
    public int getRowCount() {
        return players.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Player player = players.get(rowIndex);
        switch(columnIndex){
            case 0:
                return player.getFirstname();
            case 1:
                return player.getLastname();
            case 2:
                return player.getBirthdate().format(DateTimeFormatter.ISO_DATE);
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        String[] titles = {"Firstname", "Lastname", "Date of birth"};
        return titles[column];
    }
    
    public Player getPlayer(int row){
        return players.get(row);
    }
    
    
    
}
