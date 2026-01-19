package com.pablo.dronecup.api.service;

import com.pablo.dronecup.api.model.Championship;
import com.pablo.dronecup.api.model.HeatResult;
import com.pablo.dronecup.api.model.Pilot;
import com.pablo.dronecup.api.model.Standing;
import com.pablo.dronecup.api.repository.ChampionshipRepository;
import com.pablo.dronecup.api.repository.HeatResultRepository;
import com.pablo.dronecup.api.repository.PilotRepository;
import com.pablo.dronecup.api.repository.StandingRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StandingService {

    private final StandingRepository standingRepository;
    private final ChampionshipRepository championshipRepository;
    private final PilotRepository pilotRepository;
    private final HeatResultRepository heatResultRepository;

    public StandingService(StandingRepository standingRepository, ChampionshipRepository championshipRepository, PilotRepository pilotRepository, HeatResultRepository heatResultRepository) {
        this.standingRepository = standingRepository;
        this.championshipRepository = championshipRepository;
        this.pilotRepository = pilotRepository;
        this.heatResultRepository = heatResultRepository;
    }

    private Championship getSimpleChampionship(){

        List<Championship> championships = championshipRepository.findAll();

        if (championships.isEmpty()){
            throw new RuntimeException("No hay ningun championship en la BD");
        }

        if (championships.size() > 1){
            throw new RuntimeException("Se esperaba 1 championship en la BD y hay " + championships.size());
        }

        return championships.get(0);

    }


    public int pointsForPosition (Integer position) {

        if (position == null) return 0;

        return switch (position) {
            case 1 -> 15;
            case 2 -> 12;
            case 3 -> 10;
            case 4 -> 9;
            case 5 -> 8;
            case 6 -> 7;
            case 7 -> 6;
            case 8 -> 5;
            case 9 -> 4;
            case 10 -> 3;
            default -> 0;
        };
    }


    public void recalculateStandings () {

        Championship championship = getSimpleChampionship();

        List<Pilot> pilots = pilotRepository.findAll();

        List<HeatResult> results = heatResultRepository.findAll();



        Map<Long, Integer> pointsBypilotId = new HashMap<>();

        for (Pilot pilot : pilots){
            pointsBypilotId.put(pilot.getId(), 0);
        }

        for (HeatResult result : results){

            Pilot pilot = result.getHeatEntry().getPilot();
            int points = pointsForPosition(result.getPosition());

            pointsBypilotId.put(
                    pilot.getId(),
                    pointsBypilotId.getOrDefault(pilot.getId(),0) + points

            );
        }


       for (Pilot pilot : pilots){

           int totalPoints = pointsBypilotId.getOrDefault(pilot.getId(), 0);

           Standing standing = standingRepository
                   .findByChampioshipAndPilotId(championship.getId(), pilot.getId())
                   .orElseGet(Standing::new);

           standing.setChampionship(championship);
           standing.setPilot(pilot);
           standing.setPoints(totalPoints);

           standingRepository.save(standing);

       }



    }



}
