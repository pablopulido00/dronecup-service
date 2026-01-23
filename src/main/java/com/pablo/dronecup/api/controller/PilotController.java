package com.pablo.dronecup.api.controller;

import com.pablo.dronecup.api.dto.PilotCreateRequest;
import com.pablo.dronecup.api.dto.PilotResponse;
import com.pablo.dronecup.api.dto.PilotUpdateRequest;
import com.pablo.dronecup.api.service.PilotService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/pilots")
public class PilotController {

    private final PilotService pilotService;

    public PilotController(PilotService pilotService) {
        this.pilotService = pilotService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PilotResponse createPilot(@Valid @RequestBody PilotCreateRequest request){
        return pilotService.createPilot(request);
    }

    @PutMapping("/{id}")
    public PilotResponse updatePilot(@PathVariable Long id, @Valid @RequestBody PilotUpdateRequest request){
        return pilotService.updatePilot(id, request);
    }

    @GetMapping("/{id}")
    public PilotResponse getPilotById(@PathVariable Long id){
        return pilotService.getPilotById(id);
    }

    @GetMapping
    public List<PilotResponse> getAllPilots(){
        return pilotService.getAllPilots();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePilot(@PathVariable Long id){
        pilotService.deletePilot(id);
    }




}
