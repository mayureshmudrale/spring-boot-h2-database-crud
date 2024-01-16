package com.bezkoder.spring.jpa.h2.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.jpa.h2.model.Seats;
import com.bezkoder.spring.jpa.h2.model.Ticket;
import com.bezkoder.spring.jpa.h2.repository.SeatsRepository;
import com.bezkoder.spring.jpa.h2.repository.TicketRepository;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    private Map<String, Integer> seatsPerSection = new HashMap<>();
    private Map<String,ArrayList<Integer>> seatsallocation = new HashMap<>();
    

    public Ticket bookTicket(String userName, String userEmail, double pricePaid,String toLocation,String fromLocation) {
        // Implement logic to allocate seat and save the ticket to the database
        // ...

        Ticket ticket = new Ticket();
        ticket.setToLocation(toLocation);
        ticket.setFromLocation(fromLocation);
        ticket.setUserName(userName);
        ticket.setUserEmail(userEmail);
        ticket.setPricePaid(pricePaid);
    

        String section = allocateSection();

        if (section != null) {
            // Dummy logic to assign a seat number (can be enhanced based on your requirements)
            ticket.setSection(section);
            ticket.setSeat(generateSeatNumber(section));

            // Add the booked ticket to the list
            ticketRepository.save(ticket);

            return ticket;
        } else {
            // No available seats
            return null;
        }
    

    }

    // Implement other methods for viewing, removing, and modifying ticket

    private String allocateSection() {
        // Check if there are available seats in section A
        if (seatsPerSection.getOrDefault("A", 0) < 20) {
            seatsPerSection.put("A", seatsPerSection.getOrDefault("A", 0) + 1);
            return "A";
        }

        // Check if there are available seats in section B
        if (seatsPerSection.getOrDefault("B", 0) < 20) {
            seatsPerSection.put("B", seatsPerSection.getOrDefault("B", 0) + 1);
            return "B";
        }

        // No available seats in either section
        return null;
    }

    private String generateSeatNumber(String section) {
        // Dummy logic to generate a seat number based on the section
        return section + seatsPerSection.get(section);
    }

     public List<Ticket> getTicketsBySection(String section) {
        List<Ticket> bookedTickets = ticketRepository.findAll();
        return bookedTickets.stream()
                .filter(ticket -> section.equals(ticket.getSection()))
                .collect(Collectors.toList());
    }
    
    public List<Ticket> getTicketsById(Long id) {
        List<Ticket> bookedTickets = ticketRepository.findAll();
        
        

        return bookedTickets.stream()
                .filter(ticket -> id.equals(ticket.getId()))
                .collect(Collectors.toList());
    }

    public boolean deleteTicketbyid(Long id) {
        List<Ticket> ticket = getTicketsById(id);
        if(ticket!=null){
            try{
                ticketRepository.deleteById(id);
            }
            catch(Exception e){
                e.printStackTrace();
            }

            return true;
            


        }
        return false;
       
        
    }

    // public Ticket updateTicket(Long id,Ticket ticket){
    //     Optional<Ticket> ticketData = ticketRepository.findById(id);

    //     if(ticketData.isPresent()){
    //         Ticket ticket2 = ticketData.get();
    //         ticket2.setSeat(generateSeatNumber(allocateSection()));
    //         ticketRepository.save(ticket2);
    //         return ticket2;
    //     }


    //     return null;
    // }
    
    
    
}

