/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author HOME
 */
public class StaffMember implements Serializable{
    
    private int staffMemberId;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private Country nationality;

    public StaffMember(int staffMemberId, String firstname, String lastname, LocalDate birthdate, Country nationality) {
        this.staffMemberId = staffMemberId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.nationality = nationality;
    }

    public StaffMember(String firstname, String lastname, LocalDate birthdate, Country nationality) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.nationality = nationality;
    }

    public int getStaffMemberId() {
        return staffMemberId;
    }

    public void setStaffMemberId(int staffMemberId) {
        this.staffMemberId = staffMemberId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "StaffMember{" + "staffMemberId=" + staffMemberId + ", firstname=" + firstname + ", lastname=" + lastname + ", birthdate=" + birthdate + ", nationality=" + nationality + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.staffMemberId;
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
        final StaffMember other = (StaffMember) obj;
        return this.staffMemberId == other.staffMemberId;
    }
    
    
    
}
