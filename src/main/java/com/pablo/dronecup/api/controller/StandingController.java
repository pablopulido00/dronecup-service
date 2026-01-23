package com.pablo.dronecup.api.controller;

import com.pablo.dronecup.api.dto.StandingResponse;
import com.pablo.dronecup.api.service.StandingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/standings")
public class StandingController {

    private final StandingService standingService;

    public StandingController(StandingService standingService) {
        this.standingService = standingService;
    }

    @GetMapping
    public List<StandingResponse> getStandings() {
        return standingService.getStandings();
    }
}
