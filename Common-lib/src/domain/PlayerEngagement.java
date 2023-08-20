/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import enumeration.PlayerPosition;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author HOME
 */
public class PlayerEngagement implements Serializable{
    
    private Player player;
    private Roster roster;
    private PlayerPosition position;
    private int number;

    public PlayerEngagement(Player player, Roster roster, PlayerPosition position, int number) {
        this.player = player;
        this.roster = roster;
        this.position = position;
        this.number = number;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Roster getRoster() {
        return roster;
    }

    public void setRoster(Roster roster) {
        this.roster = roster;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public void setPosition(PlayerPosition position) {
        this.position = position;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.player);
        hash = 59 * hash + Objects.hashCode(this.roster);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PlayerEngagement other = (PlayerEngagement) obj;
        if (!Objects.equals(this.player, other.player)) {
            return false;
        }
        return Objects.equals(this.roster, other.roster);
    }

    
    
    
    
}
