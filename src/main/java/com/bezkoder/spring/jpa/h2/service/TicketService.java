package com.bezkoder.spring.jpa.h2.service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    int[] arrayA = new int[20] ;
    int[] arrayB = new int[20] ;
    private Map<String,int[]> seatsallocation = new HashMap<>();
    

    

    public TicketService() {
        seatsallocation.put("A", arrayA);
        seatsallocation.put("B", arrayB);
    }

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

    private int generateSeatNumber(String section) {
        // Dummy logic to generate a seat number based on the section
        List<Integer> zeroIndexex = IntStream.range(0, 19)
        .filter(i->seatsallocation.get(section)[i]==0)
        .boxed()
        .collect(Collectors.toList());
         
        
        return   zeroIndexex.get(new Random().nextInt(zeroIndexex.size()));
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
        Ticket ticket2 = ticket.get(0);
        String section= ticket2.getSection();
        List<Integer> Indexex = IntStream.range(0, 19)
        .filter(i->seatsallocation.get(section)[i]==id)
        .boxed()
        .collect(Collectors.toList());

        // Check if there are available seats in section B
        if(ticket!=null){
            try{
                if (section=="A") {
                    seatsPerSection.put("A", seatsPerSection.get("A") - 1);
                    int []  array = seatsallocation.get(section);
                    array[Indexex.get(0)]=0;
                    seatsallocation.put(section,array);
                   
                }
                if (section=="B") {
                    seatsPerSection.put("B", seatsPerSection.get("B") - 1);
                    int []  array = seatsallocation.get(section);
                    array[Indexex.get(0)]=0;
                    seatsallocation.put(section,array);
                   
                }
                ticketRepository.deleteById(id);
            }
            catch(Exception e){
                e.printStackTrace();
            }

            return true;
            


        }
        return false;
       
        
    }

    public Ticket updateTicket(Long id,Ticket ticket){
        Optional<Ticket> ticketData = ticketRepository.findById(id);
        
        if(ticketData.isPresent()){
            Ticket ticket2 = ticketData.get();
            List<Integer> zeroIndexex = IntStream.range(0, 19)
            .filter(i->seatsallocation.get(ticket.getSection())[i]==0)
            .boxed()
            .collect(Collectors.toList());
            if(zeroIndexex.contains(ticket.getSeat())){
                ticket2.setSeat(ticket.getSeat());
                ticketRepository.save(ticket2); 
                return ticket2;
            }
            
            
        }


        return null;
    }
    
    
    
}

