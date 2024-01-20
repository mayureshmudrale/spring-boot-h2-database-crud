package com.bezkoder.spring.jpa.h2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "toLocation")
    private String toLocation;
    @Column(name = "fromLocation")
    private String fromLocation;
    @Column(name = "userName")
    private String userName;
    @Column(name = "userEmail")
    private String userEmail;

    @Column(name = "pricePaid")
    private double pricePaid;
    @Column(name = "section")
    private String section;
    @Column(name = "seat")
    private int seat;
    
    public Ticket(String toLocation,String fromLocation, String userName, String userEmail, double pricePaid) {
        this.toLocation = toLocation;
        this.userName = userName;
        this.userEmail = userEmail;
        this.pricePaid = pricePaid;
        this.fromLocation=fromLocation;
    }
    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }
    public Long getId() {
        return id;
    }
    public String getFromLocation() {
        return fromLocation;
    }
    public Ticket() {
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
   
    public void setPricePaid(double pricePaid) {
        this.pricePaid = pricePaid;
    }
    public void setSection(String section) {
        this.section = section;
    }
    public void setSeat(int seat) {
        this.seat = seat;
    }
    public String getToLocation() {
        return toLocation;
    }
    public String getUserName() {
        return userName;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public double getPricePaid() {
        return pricePaid;
    }
    public String getSection() {
        return section;
    }
    public int getSeat() {
        return seat;
    }
    

}
