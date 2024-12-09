package com.oop.backend.controller;

import com.oop.backend.entity.Event;
import com.oop.backend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
@CrossOrigin

public class EventController {
    public static int ticketCount;
    @Autowired
    public EventService eventService;

    @PostMapping("/saveEvent")
    public Event saveEvent(@RequestBody Event event) {
        Event.numberOfVendor++;
        ticketCount = event.getTicketCount();
        return eventService.saveEvent(event);
    }

}
