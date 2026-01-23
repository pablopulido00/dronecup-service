package com.pablo.dronecup.api.controller;


import com.pablo.dronecup.api.dto.HeatEntryCreateRequest;
import com.pablo.dronecup.api.dto.HeatEntryResponse;
import com.pablo.dronecup.api.dto.HeatEntryUpdateRequest;
import com.pablo.dronecup.api.service.HeatEntryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/heat-entries")
public class HeatEntryController {

    private final HeatEntryService heatEntryService;

    public HeatEntryController(HeatEntryService heatEntryService) {
        this.heatEntryService = heatEntryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HeatEntryResponse createHeatEntry (@Valid @RequestBody HeatEntryCreateRequest request){
        return heatEntryService.createHeatEntry(request);
    }

    @PutMapping("/{id}")
    public HeatEntryResponse updateHeatEntry (@PathVariable Long id, @Valid @RequestBody HeatEntryUpdateRequest request){
        return heatEntryService.updateHeatEntry(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteHeatEntry(@PathVariable Long id){
        heatEntryService.deleteHeatEntry(id);


    }

}
