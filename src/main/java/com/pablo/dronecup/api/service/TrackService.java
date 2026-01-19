package com.pablo.dronecup.api.service;

import com.pablo.dronecup.api.dto.EventSummary;
import com.pablo.dronecup.api.dto.TrackCreateRequest;
import com.pablo.dronecup.api.dto.TrackResponse;
import com.pablo.dronecup.api.dto.TrackUpdateRequest;
import com.pablo.dronecup.api.model.Track;
import com.pablo.dronecup.api.repository.TrackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackService {


    private final TrackRepository trackRepository;

    public TrackService (TrackRepository trackRepository){
        this.trackRepository = trackRepository;
    }

    public TrackResponse createTrack (TrackCreateRequest request){

        Track track = new Track();

        track.setName(request.getName());
        track.setLocation(request.getLocation());

        Track trackSaved = trackRepository.save(track);

        return new TrackResponse(
                trackSaved.getId(),
                trackSaved.getName(),
                trackSaved.getLocation(),
                List.of()
        );




    }


    public TrackResponse getTrackById (Long id){

        Track track = trackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Track no encontrado"));

        return new TrackResponse(
          track.getId(),
          track.getName(),
          track.getLocation(),
          track.getEvents().stream()
                  .map(event -> new EventSummary(
                          event.getId(),
                          event.getName()
                  )).toList()
        );



    }

    public List<TrackResponse> getAllTracks (){
        return trackRepository.findAll().stream()
                .map(track -> new TrackResponse(
                        track.getId(),
                        track.getName(),
                        track.getLocation(),
                        track.getEvents().stream()
                                .map(event -> new EventSummary(
                                        event.getId(),
                                        event.getName()
                                )).toList()
                )).toList();
    }

    public TrackResponse updateTrack (Long id, TrackUpdateRequest request){

        Track track = trackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Track no encontrado"));

        if (request.getName() != null){
            track.setName(request.getName());
        }

        if (request.getLocation() != null){
            track.setLocation(request.getLocation());
        }

        Track trackUpdated = trackRepository.save(track);

        return new TrackResponse(
                trackUpdated.getId(),
                trackUpdated.getName(),
                trackUpdated.getLocation(),
                trackUpdated.getEvents().stream()
                        .map(event -> new EventSummary(
                                event.getId(),
                                event.getName()
                        )).toList()
        );

    }

    public void deleteTrack (Long id){

        Track track = trackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Track no encontrado"));


        if (!track.getEvents().isEmpty()){
            throw new RuntimeException(("No se puede borrar un track con eventos asociados");
        }

        trackRepository.delete(track);

    }

}
