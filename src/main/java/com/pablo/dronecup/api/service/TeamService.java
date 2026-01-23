package com.pablo.dronecup.api.service;

import com.pablo.dronecup.api.dto.PilotSummary;
import com.pablo.dronecup.api.dto.TeamCreateRequest;
import com.pablo.dronecup.api.dto.TeamResponse;
import com.pablo.dronecup.api.dto.TeamUpdateRequest;
import com.pablo.dronecup.api.exception.ConflictException;
import com.pablo.dronecup.api.exception.NotFoundException;
import com.pablo.dronecup.api.model.Team;
import com.pablo.dronecup.api.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public TeamResponse createTeam(TeamCreateRequest request) {

        Team team = new Team();
        team.setName(request.getName());
        team.setCountry(request.getCountry());

        Team savedTeam = teamRepository.save(team);

        return new TeamResponse(
                savedTeam.getId(),
                savedTeam.getName(),
                savedTeam.getCountry(),
                List.of()
        );
    }

    public TeamResponse getTimeById(Long id) {

        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Equipo no encontrado"));

        return new TeamResponse(
                team.getId(),
                team.getName(),
                team.getCountry(),
                team.getPilots().stream()
                        .map(pilot -> new PilotSummary(
                                pilot.getId(),
                                pilot.getName()
                        ))
                        .toList()
        );
    }

    public List<TeamResponse> getAllTeams() {

        return teamRepository.findAll().stream()
                .map(team -> new TeamResponse(
                        team.getId(),
                        team.getName(),
                        team.getCountry(),
                        team.getPilots().stream()
                                .map(pilot -> new PilotSummary(
                                        pilot.getId(),
                                        pilot.getName()
                                ))
                                .toList()
                ))
                .toList();
    }

    public TeamResponse updateTeam(Long id, TeamUpdateRequest request) {

        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Equipo no encontrado"));

        if (request.getName() != null) {
            team.setName(request.getName());
        }

        if (request.getCountry() != null) {
            team.setCountry(request.getCountry());
        }

        Team updatedTeam = teamRepository.save(team);

        return new TeamResponse(
                updatedTeam.getId(),
                updatedTeam.getName(),
                updatedTeam.getCountry(),
                updatedTeam.getPilots().stream()
                        .map(pilot -> new PilotSummary(
                                pilot.getId(),
                                pilot.getName()
                        ))
                        .toList()
        );
    }

    public void deleteTeam(Long id) {

        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Equipo no encontrado"));

        if (!team.getPilots().isEmpty()) {
            throw new ConflictException("No se puede borrrar un equipo con pilotos");
        }

        teamRepository.delete(team);
    }
}
