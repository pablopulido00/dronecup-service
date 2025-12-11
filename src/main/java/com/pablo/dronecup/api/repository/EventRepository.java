package com.pablo.dronecup.api.repository;

import com.pablo.dronecup.api.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
