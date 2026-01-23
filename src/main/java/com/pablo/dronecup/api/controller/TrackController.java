package com.pablo.dronecup.api.controller;


import com.pablo.dronecup.api.dto.TrackCreateRequest;
import com.pablo.dronecup.api.dto.TrackResponse;
import com.pablo.dronecup.api.dto.TrackUpdateRequest;
import com.pablo.dronecup.api.service.TrackService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tracks")
public class TrackController {

    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrackResponse createTrack(@Valid @RequestBody TrackCreateRequest request) {
        return trackService.createTrack(request);
    }

    @GetMapping("/{id}")
    public TrackResponse getTrackById(@PathVariable Long id) {
        return trackService.getTrackById(id);
    }

    @GetMapping
    public List<TrackResponse> getAllTracks(){
        return trackService.getAllTracks();
    }

    @PutMapping("/{id}")
    public TrackResponse updateTrack (@PathVariable Long id,@Valid @RequestBody TrackUpdateRequest request){
        return trackService.updateTrack(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrack (@PathVariable Long id){
        trackService.deleteTrack(id);
    }



}
