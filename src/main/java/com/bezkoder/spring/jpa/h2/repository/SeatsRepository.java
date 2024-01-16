package com.bezkoder.spring.jpa.h2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.jpa.h2.model.Seats;
import com.bezkoder.spring.jpa.h2.model.Ticket;

public interface SeatsRepository extends JpaRepository<Seats,String>{
    
}
