package com.oop.backend.repo;

import com.oop.backend.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event,Integer> {
    Optional<Event> findByEventName(String fileName);
}
