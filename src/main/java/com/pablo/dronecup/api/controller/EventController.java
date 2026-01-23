package com.pablo.dronecup.api.controller;


import com.pablo.dronecup.api.dto.EventCreateRequest;
import com.pablo.dronecup.api.dto.EventResponse;
import com.pablo.dronecup.api.dto.EventUpdateRequest;
import com.pablo.dronecup.api.model.Event;
import com.pablo.dronecup.api.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {



    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventResponse createEvent (@Valid @RequestBody EventCreateRequest request) {
        return eventService.createEvent(request);
    }

    @GetMapping("/{id}")
    public EventResponse getEventById (@PathVariable Long id){
        return eventService.getEventById(id);
    }

    @PutMapping("/{id}")
    public EventResponse updateEvent (@PathVariable Long id, @Valid @RequestBody EventUpdateRequest request){
        return eventService.updateEvent(id, request);
    }

    @GetMapping
    public List<EventResponse> getAllEvents(){
        return eventService.getAllEvents();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable Long id){
        eventService.deleteEvent(id);
    }

}
