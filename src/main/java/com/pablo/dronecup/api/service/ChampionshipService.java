package com.pablo.dronecup.api.service;


import com.pablo.dronecup.api.dto.ChampionshipCreateRequest;
import com.pablo.dronecup.api.dto.ChampionshipResponse;
import com.pablo.dronecup.api.dto.ChampionshipUpdateRequest;
import com.pablo.dronecup.api.exception.ConflictException;
import com.pablo.dronecup.api.exception.NotFoundException;
import com.pablo.dronecup.api.model.Championship;
import com.pablo.dronecup.api.repository.ChampionshipRepository;
import com.pablo.dronecup.api.repository.StandingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChampionshipService {


    private final ChampionshipRepository championshipRepository;
    private final StandingRepository standingRepository;

    public ChampionshipService(ChampionshipRepository championshipRepository, StandingRepository standingRepository) {
        this.championshipRepository = championshipRepository;
        this.standingRepository = standingRepository;
    }

    private Championship getSingleChampionship() {
        List<Championship> championships = championshipRepository.findAll();

        if (championships.isEmpty()) {
            throw new NotFoundException("No hay ningún Championship en la BD");
        }

        if (championships.size() > 1) {
            throw new ConflictException("Se esperaba 1 Championship en la BD, pero hay " + championships.size());
        }

        return championships.get(0);
    }

    public ChampionshipResponse createChampionship (ChampionshipCreateRequest request){

        if (championshipRepository.count() > 0){
            throw new ConflictException("Ya existe un Championship en la BD. Solo se permite 1");
        }

        Championship championship = new Championship();
        championship.setName(request.getName());
        championship.setSeasonYear(request.getSeasonYear());

        Championship championshipSaved = championshipRepository.save(championship);

        return new ChampionshipResponse(
                championshipSaved.getId(),
                championshipSaved.getName().trim(),
                championshipSaved.getSeasonYear()
        );


    }

    public Championship getChampionship(){
        return getSingleChampionship();
    }

    public ChampionshipResponse getChampionshipResponse() {
        Championship championship = getChampionship(); // reutiliza el que ya tienes
        return new ChampionshipResponse(
                championship.getId(),
                championship.getName().trim(),
                championship.getSeasonYear()
        );
    }



    public ChampionshipResponse updateChampionship (ChampionshipUpdateRequest request){

        Championship championship = getChampionship();

        if (request.getName() != null){
            championship.setName(request.getName());
        }

        if (request.getSeasonYear() != null){
            championship.setSeasonYear(request.getSeasonYear());
        }

        Championship championshipUpdated = championshipRepository.save(championship);

        return new ChampionshipResponse(
                championshipUpdated.getId(),
                championshipUpdated.getName(),
                championshipUpdated.getSeasonYear()
        );


    }

    public void deleteChampionship (){

        Championship championship = getChampionship();

        long standingsCount = standingRepository.countByChampionshipId(championship.getId());


        if (standingsCount > 0){
            throw new ConflictException("No se puede borrar un championship con standings asociados");
        }

        championshipRepository.delete(championship);

    }



}
