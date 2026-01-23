package com.pablo.dronecup.api.controller;

import com.pablo.dronecup.api.dto.ChampionshipCreateRequest;
import com.pablo.dronecup.api.dto.ChampionshipResponse;
import com.pablo.dronecup.api.dto.ChampionshipUpdateRequest;
import com.pablo.dronecup.api.service.ChampionshipService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/championship")
public class ChampionshipController {

    private final ChampionshipService championshipService;

    public ChampionshipController(ChampionshipService championshipService) {
        this.championshipService = championshipService;
    }

    @GetMapping
    public ChampionshipResponse getChampionship(){
        return championshipService.getChampionshipResponse();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ChampionshipResponse createChampionship (@Valid @RequestBody ChampionshipCreateRequest request){
        return championshipService.createChampionship(request);
    }
    @PutMapping
    public ChampionshipResponse updateChampionship (@Valid @RequestBody ChampionshipUpdateRequest request){
        return championshipService.updateChampionship(request);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteChampionship (){
        championshipService.deleteChampionship();
    }

}
