package com.bezkoder.spring.jpa.h2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.jpa.h2.model.Ticket;
import com.bezkoder.spring.jpa.h2.repository.SeatsRepository;
import com.bezkoder.spring.jpa.h2.repository.TicketRepository;
import com.bezkoder.spring.jpa.h2.service.TicketService;
@RestController
@RequestMapping("/api")
public class TicketController {
   

   @Autowired
   TicketService ticketService;
   @PostMapping("/book")
   public ResponseEntity<Ticket> bookTicket(@RequestBody Ticket ticket) {
       // Perform logic to allocate a seat in section A or B String userName, String userEmail, double pricePaid,String toLocation,String fromLocation
       Ticket ticket2 = ticketService.bookTicket(ticket.getUserName(),ticket.getUserEmail(),ticket.getPricePaid(),ticket.getToLocation(),ticket.getFromLocation());

       if (ticket != null) {
           return new ResponseEntity<>(ticket2, HttpStatus.CREATED);
       } else {
           return new ResponseEntity<>(HttpStatus.IM_USED);
       }
   }


   @GetMapping("/section/{section}")
    public ResponseEntity<List<Ticket>> getTicketsBySection(@PathVariable String section) {
        List<Ticket> tickets = ticketService.getTicketsBySection(section);

        if (!tickets.isEmpty()) {
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/receipt/{id}")
    public ResponseEntity<List<Ticket>> getTicketsBySection(@PathVariable Long id) {
        List<Ticket> tickets = ticketService.getTicketsById(id);

        if (tickets!=null) {
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/receipt/{id}")
    public ResponseEntity deleteTicketbyid(@PathVariable Long id) {
        boolean flag = ticketService.deleteTicketbyid(id);

        if (flag) {
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

  //   @PutMapping("/modify/{id}")
  // public ResponseEntity<Ticket> updateSeat(@PathVariable("id") long id, @RequestBody Ticket ticket) {
  //     Ticket ticket2 = ticketService.updateTicket(id, ticket);

  //   if (ticket2!=null) {
    
  //     return new ResponseEntity<>(ticket2, HttpStatus.OK);
  //   } else {
  //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  //   }
  // }
}

