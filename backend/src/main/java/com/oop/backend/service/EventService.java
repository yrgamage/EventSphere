package com.oop.backend.service;

import com.oop.backend.dto.Eventdto;
import com.oop.backend.entity.Event;
import com.oop.backend.repo.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.io.File;
import java.util.*;
import java.io.IOException;

@Service
@Transactional
public class EventService {
    @Autowired
    private final EventRepository eventRepository;
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    private final String FOLDER_PATH = "D:\\sem 3\\OOP\\oop cw\\frontend\\src\\assets\\Images";
    public Event saveEvent(MultipartFile file, String eventName, int ticketCount, String eventDate,
                           String eventTime, String eventLocation, int ticketPrice) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is empty or null");
        }

        File directory = new File(FOLDER_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        String filePath = FOLDER_PATH + file.getOriginalFilename();
        Event event = Event.builder()
                .eventName(eventName)
                .ticketCount(ticketCount)
                .eventDate(eventDate)
                .eventTime(eventTime)
                .eventLocation(eventLocation)
                .ticketPrice(ticketPrice)
                .eventImage(filePath)
                .build();

        event = eventRepository.save(event);

        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file: " + e.getMessage(), e);
        }

        return event;
    }



    public List<Eventdto> getEventsWithImages() throws IOException {
        List<Event> events = eventRepository.findAll();
        List<Eventdto> eventsWithImages = new ArrayList<>();

        for (Event event : events) {
            String filePath = event.getEventImage();

            File imageFile = new File(filePath);
            byte[] imageBytes = null;

            if (imageFile.exists()) {
                imageBytes = Files.readAllBytes(imageFile.toPath());
            } else {
                System.out.println("Image file not found for event: " + event.getEventName());
            }

            Eventdto eventWithImageDTO = new Eventdto();
            eventWithImageDTO.setEvent(event);
            eventWithImageDTO.setImage(imageBytes);

            eventsWithImages.add(eventWithImageDTO);
        }

        return eventsWithImages;
    }





}