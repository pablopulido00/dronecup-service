package com.pablo.dronecup.api.controller;

import com.pablo.dronecup.api.dto.DroneCreateRequest;
import com.pablo.dronecup.api.dto.DroneResponse;
import com.pablo.dronecup.api.dto.DroneUpdateRequest;
import com.pablo.dronecup.api.service.DroneService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drones")
public class DroneController {

    private final DroneService droneService;

    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DroneResponse createDrone(@Valid @RequestBody DroneCreateRequest request){
        return droneService.createDrone(request);
    }

    @GetMapping("/{id}")
    public  DroneResponse getDroneById (@PathVariable Long id){
        return droneService.getDroneById(id);
    }

    @GetMapping
    public List<DroneResponse> getAllDrones(){
        return droneService.getAllDrones();
    }

    @PutMapping("/{id}")
    public DroneResponse updateDrone (@PathVariable Long id, @Valid @RequestBody DroneUpdateRequest request){
        return droneService.updateDrone(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDrone(@PathVariable Long id){
        droneService.deleteDrone(id);
    }

}
