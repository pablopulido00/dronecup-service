package com.pablo.dronecup.api.controller;


import com.pablo.dronecup.api.dto.TeamCreateRequest;
import com.pablo.dronecup.api.dto.TeamResponse;
import com.pablo.dronecup.api.dto.TeamUpdateRequest;
import com.pablo.dronecup.api.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<TeamResponse> getAllTeams(){
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}")
    public TeamResponse getTeamById(@PathVariable Long id){
        return teamService.getTeamById(id);
    }

    @PutMapping("/{id}")
    public TeamResponse updateTeam(@PathVariable Long id, @Valid @RequestBody TeamUpdateRequest request){
            return teamService.updateTeam(id, request);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamResponse createTeam(@Valid@RequestBody TeamCreateRequest request){
        return teamService.createTeam(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeam(@PathVariable Long id){
        teamService.deleteTeam(id);
    }



}
