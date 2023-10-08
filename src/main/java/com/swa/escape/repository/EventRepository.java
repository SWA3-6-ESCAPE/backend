package com.swa.escape.repository;

import com.swa.escape.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface EventRepository extends JpaRepository<Event, Integer> {


    @Query("SELECT e FROM Event e")
    public List<Event> findAllEvents();
}
