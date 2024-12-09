package com.oop.backend.controller;

import com.oop.backend.entity.Ticket;
import com.oop.backend.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
@CrossOrigin

public class TicketController {
    public static int ticketPerCustomer;

    @Autowired
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping ("/saveTicket")
    public Ticket saveTicket(@RequestBody Ticket ticket) {
        Ticket.numberOfCustomer++;
        ticketPerCustomer = ticket.getTicketCount();
        return ticketService.saveTicket(ticket);
    }
}




