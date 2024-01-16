package com.bezkoder.spring.jpa.h2.model;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "seats")
public class Seats {
    @Id
    private String coach;

    @Column(name = "seat_map")
    @ElementCollection(targetClass = Integer.class)
    private ArrayList<Integer> seat_map;

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public ArrayList<Integer> getSeat_map() {
        return seat_map;
    }

    public void setSeat_map(ArrayList<Integer> seat_map) {
        this.seat_map = seat_map;
    }

    
}
