package com.pablo.dronecup.api.controller;

import com.pablo.dronecup.api.dto.HeatCreateRequest;
import com.pablo.dronecup.api.dto.HeatResponse;
import com.pablo.dronecup.api.dto.HeatUpdateRequest;
import com.pablo.dronecup.api.service.HeatService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/heats")
public class HeatController {

    private final HeatService heatService;

    public HeatController(HeatService heatService) {
        this.heatService = heatService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HeatResponse createHeat(@RequestBody HeatCreateRequest request){
            return heatService.createHeat(request);
    }

    @GetMapping("/{id}")
    public HeatResponse getHeatById(@PathVariable Long id){
        return heatService.getHeatById(id);
    }

    @GetMapping
    public List<HeatResponse> getAllHeats(){
        return heatService.getAllHeats();
    }

    @PutMapping("/{id}")
    public HeatResponse updateHeat(@PathVariable Long id, @RequestBody HeatUpdateRequest request){
        return heatService.updateHeat(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHeat(@PathVariable Long id){
        heatService.deleteHeat(id);
    }





}
