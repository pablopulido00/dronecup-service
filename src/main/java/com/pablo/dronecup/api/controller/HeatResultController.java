package com.pablo.dronecup.api.controller;


import com.pablo.dronecup.api.dto.HeatResultCreateRequest;
import com.pablo.dronecup.api.dto.HeatResultResponse;
import com.pablo.dronecup.api.dto.HeatResultUpdateRequest;
import com.pablo.dronecup.api.service.HeatResultService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/heat-results")
public class HeatResultController {


    private final HeatResultService heatResultService;

    public HeatResultController(HeatResultService heatResultService) {
        this.heatResultService = heatResultService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HeatResultResponse createHeatResult (@Valid @RequestBody HeatResultCreateRequest request){
        return heatResultService.createHeatResult(request);
    }


    @PutMapping("/{id}")
    public HeatResultResponse updateHeatResult(@PathVariable Long id, @Valid @RequestBody HeatResultUpdateRequest request){
        return heatResultService.updateHeatResult(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHeatResult (@PathVariable Long id){
        heatResultService.deleteHeatResult(id);
    }
}
