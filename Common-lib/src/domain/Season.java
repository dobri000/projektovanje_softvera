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
public class Season implements Serializable {
    
    private int seasonId;
    private int startYear;
    private int endYear;

    public Season(int seasonId, int startYear, int endYear) {
        this.seasonId = seasonId;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public Season(int startYear, int endYear) {
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    @Override
    public String toString() {
        return startYear + "/" + endYear;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.seasonId;
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
        final Season other = (Season) obj;
        return this.seasonId == other.seasonId;
    }

        
    
    
    
}
