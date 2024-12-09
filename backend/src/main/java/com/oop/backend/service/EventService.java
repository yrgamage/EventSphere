package com.oop.backend.service;

import com.oop.backend.entity.Event;
import com.oop.backend.repo.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class EventService {
    private final EventRepository eventRepository;
    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    public Event saveEvent(Event event){
        return eventRepository.save(event);
    }


}
