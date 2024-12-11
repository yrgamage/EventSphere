package com.oop.backend.controller;

import com.oop.backend.dto.Eventdto;
import com.oop.backend.entity.Event;
import com.oop.backend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/event")
@CrossOrigin

public class EventController {
    public static int TicketCount;
    @Autowired
    public EventService eventService;

    @PostMapping("/saveEvent")
    public ResponseEntity<?> uploadImageToFileSystem(
            @RequestParam("image") MultipartFile file,
            @RequestParam("eventName") String eventName,
            @RequestParam("ticketCount") int ticketCount,
            @RequestParam("eventDate") String eventDate,
            @RequestParam("eventTime") String eventTime,
            @RequestParam("eventLocation") String eventLocation,
            @RequestParam("ticketPrice") int ticketPrice) throws IOException {

        // Call the modified saveEvent method with all parameters
        Event savedEvent = eventService.saveEvent(file, eventName, ticketCount, eventDate, eventTime, eventLocation, ticketPrice);
        Event.numberOfVendor++;
        TicketCount = ticketCount;


        // Return the saved event details in the response
        return ResponseEntity.status(HttpStatus.OK)
                .body(savedEvent);
    }

    @GetMapping("/eventsWithImages")
    public ResponseEntity<List<Eventdto>> getEventsWithImages() throws IOException {
        List<Eventdto> eventsWithImages = eventService.getEventsWithImages();
        return ResponseEntity.ok(eventsWithImages);
    }




}
