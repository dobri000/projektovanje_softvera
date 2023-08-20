/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author HOME
 */
public class Roster implements Serializable {
    
    private Team team;
    private int rosterId;
    private Season season;

    public Roster(Team team, int rosterId, Season season) {
        this.team = team;
        this.rosterId = rosterId;
        this.season = season;
    }

    public Roster(Team team, Season season) {
        this.team = team;
        this.season = season;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getRosterId() {
        return rosterId;
    }

    public void setRosterId(int rosterId) {
        this.rosterId = rosterId;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.team);
        hash = 97 * hash + this.rosterId;
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
        final Roster other = (Roster) obj;
        if (this.rosterId != other.rosterId) {
            return false;
        }
        return Objects.equals(this.team, other.team);
    }
    
    
    
}
