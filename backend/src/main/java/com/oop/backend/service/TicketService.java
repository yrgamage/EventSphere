package com.oop.backend.service;

import com.oop.backend.entity.Ticket;
import com.oop.backend.repo.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional

public class TicketService {
    public final TicketRepository ticketRepository;
    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket saveTicket(Ticket ticket){
        return ticketRepository.save(ticket);
    }


}
