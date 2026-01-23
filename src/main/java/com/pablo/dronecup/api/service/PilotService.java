package com.pablo.dronecup.api.service;

import com.pablo.dronecup.api.dto.*;
import com.pablo.dronecup.api.exception.ConflictException;
import com.pablo.dronecup.api.exception.NotFoundException;
import com.pablo.dronecup.api.model.Drone;
import com.pablo.dronecup.api.model.Pilot;
import com.pablo.dronecup.api.model.Team;
import com.pablo.dronecup.api.repository.DroneRepository;
import com.pablo.dronecup.api.repository.PilotRepository;
import com.pablo.dronecup.api.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PilotService {

    private final PilotRepository pilotRepository;
    private final TeamRepository teamRepository;
    private final DroneRepository droneRepository;

    public PilotService(PilotRepository pilotRepository, TeamRepository teamRepository, DroneRepository droneRepository) {
        this.pilotRepository = pilotRepository;
        this.teamRepository = teamRepository;
        this.droneRepository = droneRepository;
    }

    public PilotResponse createPilot(PilotCreateRequest request) {

        Team team = teamRepository.findById(request.getTeamId())
                .orElseThrow(() -> new NotFoundException("Equipo no encontrado"));

        Drone drone = droneRepository.findById(request.getDroneId())
                .orElseThrow(() -> new NotFoundException("Drone no encontrado"));

        if (drone.getPilot() != null) {
            throw new ConflictException("El drone ya esta asignado a otro piloto");
        }

        Pilot pilot = new Pilot();

        pilot.setName(request.getName());
        pilot.setNationality(request.getNationality());
        pilot.setAge(request.getAge());
        pilot.setTeam(team);
        pilot.setDrone(drone);

        Pilot savedPilot = pilotRepository.save(pilot);

        return new PilotResponse(
                savedPilot.getId(),
                savedPilot.getName(),
                savedPilot.getNationality(),
                savedPilot.getAge(),

                new TeamSummary(
                        team.getId(),
                        team.getName(),
                        team.getCountry()
                ),

                new DroneSummary(
                        drone.getId(),
                        drone.getModel()
                )

        );
    }

    public PilotResponse updatePilot (Long id, PilotUpdateRequest request){

        Pilot pilot = pilotRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Piloto no encontrado"));


        if (request.getName() != null){
            pilot.setName(request.getName());
        }

        if (request.getNationality() != null){
            pilot.setNationality(request.getNationality());
        }

        if (request.getAge() != null){
            pilot.setAge(request.getAge());
        }



        Pilot pilotUpdated = pilotRepository.save(pilot);


        return new PilotResponse(
                pilotUpdated.getId(),
                pilotUpdated.getName(),
                pilotUpdated.getNationality(),
                pilotUpdated.getAge(),
                new TeamSummary(
                        pilotUpdated.getTeam().getId(),
                        pilotUpdated.getTeam().getName(),
                        pilotUpdated.getTeam().getCountry()
                ),
                new DroneSummary(
                        pilotUpdated.getDrone().getId(),
                        pilotUpdated.getDrone().getModel()

                )
        );
    }

    public PilotResponse getPilotById (Long id){
        Pilot pilot = pilotRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Piloto no encontrado"));

        return new PilotResponse(
                pilot.getId(),
                pilot.getName(),
                pilot.getNationality(),
                pilot.getAge(),
                new TeamSummary(
                        pilot.getTeam().getId(),
                        pilot.getTeam().getName(),
                        pilot.getTeam().getCountry()
                ),
                new DroneSummary(
                        pilot.getDrone().getId(),
                        pilot.getDrone().getModel()
                )
        );
    }

    public List<PilotResponse> getAllPilots(){
        return pilotRepository.findAll().stream()
                .map(pilot -> new PilotResponse(
                        pilot.getId(),
                        pilot.getName(),
                        pilot.getNationality(),
                        pilot.getAge(),
                        new TeamSummary(
                                pilot.getTeam().getId(),
                                pilot.getTeam().getName(),
                                pilot.getTeam().getCountry()
                        ),
                        new DroneSummary(
                                pilot.getDrone().getId(),
                                pilot.getDrone().getModel()
                        )
                ))
                .toList();
    }

    public void deletePilot (Long id){

        Pilot pilot = pilotRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Piloto no encontrado"));

        pilot.setDrone(null);

        pilotRepository.delete(pilot);
    }
}
