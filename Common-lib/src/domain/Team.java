/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;

/**
 *
 * @author HOME
 */
public class Team implements Serializable{
    private int teamId;
    private String teamName;
    private int founded;
    private Country country;
    private Hall hall;

    

    public Team(int teamId, String name, int founded, Country country, Hall hall) {
        this.teamId = teamId;
        this.teamName = name;
        this.founded = founded;
        this.country = country;
        this.hall = hall;
    }

    public Team(String name, int founded, Country country, Hall hall) {
        this.teamName = name;
        this.founded = founded;
        this.country = country;
        this.hall = hall;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getFounded() {
        return founded;
    }

    public void setFounded(int founded) {
        this.founded = founded;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }
    
    @Override
    public String toString() {
        return "Team{" + "teamId=" + teamId + ", name=" + teamName + ", founded=" + founded + ", country=" + country + ", hall=" + hall + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.teamId;
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
        final Team other = (Team) obj;
        return this.teamId == other.teamId;
    }
    
    
    
}
