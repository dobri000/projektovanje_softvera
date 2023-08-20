/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import enumeration.StaffMemberPosition;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author HOME
 */
public class StaffMemberEngagement implements Serializable{
    
    private StaffMember staffMember;
    private Roster roster;
    private StaffMemberPosition position;

    public StaffMemberEngagement(StaffMember staffMember, Roster roster, StaffMemberPosition position) {
        this.staffMember = staffMember;
        this.roster = roster;
        this.position = position;
    }

    public StaffMember getStaffMember() {
        return staffMember;
    }

    public void setStaffMember(StaffMember staffMember) {
        this.staffMember = staffMember;
    }

    public Roster getRoster() {
        return roster;
    }

    public void setRoster(Roster roster) {
        this.roster = roster;
    }

    public StaffMemberPosition getPosition() {
        return position;
    }

    public void setPosition(StaffMemberPosition position) {
        this.position = position;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.staffMember);
        hash = 61 * hash + Objects.hashCode(this.roster);
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
        final StaffMemberEngagement other = (StaffMemberEngagement) obj;
        if (!Objects.equals(this.staffMember, other.staffMember)) {
            return false;
        }
        return Objects.equals(this.roster, other.roster);
    }
    
    
    
}
