package com.pablo.dronecup.api.service;

import com.pablo.dronecup.api.dto.*;
import com.pablo.dronecup.api.model.Event;
import com.pablo.dronecup.api.model.Track;
import com.pablo.dronecup.api.repository.EventRepository;
import com.pablo.dronecup.api.repository.TrackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final TrackRepository trackRepository;

    public EventService(EventRepository eventRepository, TrackRepository trackRepository) {
        this.eventRepository = eventRepository;
        this.trackRepository = trackRepository;
    }

    public EventResponse createEvent (EventCreateRequest request){

        Track track = trackRepository.findById(request.getTrackId())
                .orElseThrow(()-> new RuntimeException("Track no encontrado"));

        Event event = new Event();

        event.setName(request.getName());
        event.setDate(request.getDate());
        event.setTrack(track);

        Event eventSaved = eventRepository.save(event);

        return new EventResponse(
                eventSaved.getId(),
                eventSaved.getName(),
                eventSaved.getDate(),
                new TrackSummary(
                        track.getId(),
                        track.getName(),
                        track.getLocation()
                ),
                List.of()
        );

    }

    public EventResponse getEventById (Long id){

        Event event =  eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event no encontrado"));

        return new EventResponse (
                event.getId(),
                event.getName(),
                event.getDate(),
                new TrackSummary(
                     event.getTrack().getId(),
                     event.getTrack().getName(),
                     event.getTrack().getLocation()
                ),
                event.getHeats().stream()
                        .map(heat -> new HeatSummary(
                               heat.getId(),
                               heat.getName()
                        )).toList()
        );

    }


    public EventResponse updateEvent (Long id, EventUpdateRequest request){

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event no encontrado"));

        if (request.getName() != null){
            event.setName(request.getName());
        }

        if (request.getDate() != null){
            event.setDate(request.getDate());
        }

        if (request.getTrackId() != null){


            Track newTrack = trackRepository.findById(request.getTrackId())
                    .orElseThrow(() -> new RuntimeException("Track no encontrado"));

            event.setTrack(newTrack);

        }

        Event eventUpdated = eventRepository.save(event);


        return new EventResponse(
                eventUpdated.getId(),
                eventUpdated.getName(),
                eventUpdated.getDate(),
                new TrackSummary(
                        eventUpdated.getTrack().getId(),
                        eventUpdated.getTrack().getName(),
                        eventUpdated.getTrack().getLocation()
                ),
                eventUpdated.getHeats().stream()
                        .map(heat -> new HeatSummary(
                                heat.getId(),
                                heat.getName()
                        )).toList()

        );


    }


    public List<EventResponse> getAllEvents (){
        return eventRepository.findAll().stream()
                .map(event -> new EventResponse(
                        event.getId(),
                        event.getName(),
                        event.getDate(),
                        new TrackSummary(
                                event.getTrack().getId(),
                                event.getTrack().getName(),
                                event.getTrack().getLocation()
                        ),
                        event.getHeats().stream()
                                .map( heat -> new HeatSummary(
                                        heat.getId(),
                                        heat.getName()
                                        )
                                ).toList()
                        )
                ).toList();
    }

    public void deleteEvent (Long id){
        Event event =  eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        if (!event.getHeats().isEmpty()){
            throw new RuntimeException("No se puede borrar un Event con heats asociados");

        }

        eventRepository.delete(event);
    }
}
