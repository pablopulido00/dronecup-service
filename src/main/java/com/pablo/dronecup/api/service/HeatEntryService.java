package com.pablo.dronecup.api.service;

import com.pablo.dronecup.api.dto.HeatEntryCreateRequest;
import com.pablo.dronecup.api.dto.HeatEntryResponse;
import com.pablo.dronecup.api.dto.HeatEntryUpdateRequest;
import com.pablo.dronecup.api.dto.HeatResultSummary;
import com.pablo.dronecup.api.model.Heat;
import com.pablo.dronecup.api.model.HeatEntry;
import com.pablo.dronecup.api.model.Pilot;
import com.pablo.dronecup.api.repository.HeatEntryRepository;
import com.pablo.dronecup.api.repository.HeatRepository;
import com.pablo.dronecup.api.repository.PilotRepository;
import org.springframework.stereotype.Service;

@Service
public class HeatEntryService {


    private final HeatEntryRepository heatEntryRepository;
    private final PilotRepository pilotRespository;
    private final HeatRepository heatRepository;

    public HeatEntryService(HeatEntryRepository heatEntryRepository, PilotRepository pilotRespository, HeatRepository heatRepository) {
        this.heatEntryRepository = heatEntryRepository;
        this.pilotRespository = pilotRespository;
        this.heatRepository = heatRepository;
    }


    public HeatEntryResponse createHeatEntry (HeatEntryCreateRequest request){

        Pilot pilot = pilotRespository.findById(request.getPilotId())
                .orElseThrow(() -> new RuntimeException("Piloto no encontrado"));

        Heat heat = heatRepository.findById(request.getHeatId())
                .orElseThrow(() -> new RuntimeException("Heat no encontrado"));

        if (heatEntryRepository.existsByHeatIdAndPilotId(heat.getId(), pilot.getId())){
            throw new RuntimeException("El piloto " + pilot.getName() +" ya esta inscrito en ese heat");
        }

        if (request.getStartPosicion() == null || request.getStartPosicion() < 1){
            throw new RuntimeException("El HeatEntry debe tener una posicion inicial y debe ser 1 o mayor");
        }

        if (request.getStartPosicion() < 1 || request.getStartPosicion() > 10) {
            throw new RuntimeException("La posición de salida debe estar entre 1 y 10");
        }

        if (heatEntryRepository.existsByHeatIdAndStartPosition(heat.getId(), request.getStartPosicion())){
            throw new RuntimeException("La posicion de salida" + request.getStartPosicion() + " ya esta ocupada" );

        }

        HeatEntry heatEntry = new HeatEntry();

        heatEntry.setStartPosition(request.getStartPosicion());
        heatEntry.setPilot(pilot);
        heatEntry.setHeat(heat);


        HeatEntry heatEntrySaved = heatEntryRepository.save(heatEntry);


        return new HeatEntryResponse(
                heatEntrySaved.getId(),
                heatEntrySaved.getStartPosition(),
                heatEntrySaved.getPilot().getId(),
                heatEntrySaved.getHeat().getId(),
                heatEntrySaved.getHeatResult() == null
                ? null
                : new HeatResultSummary(
                        heatEntrySaved.getHeatResult().getPosition(),
                        heatEntrySaved.getHeatResult().getBestLapTime(),
                        heatEntrySaved.getHeatResult().getTotalTime(),
                        heatEntrySaved.getHeatResult().getPenalties()
                )


        );

    }

    public HeatEntryResponse updateHeatEntry(Long id, HeatEntryUpdateRequest request) {

        HeatEntry heatEntry = heatEntryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HeatEntry no encontrado"));

        if (request.getHeatId() != null) {
            throw new RuntimeException("No se puede cambiar el heat de un heatEntry");
        }

        if (request.getPilotId() != null) {
            throw new RuntimeException("No se puede camniar un piloto de heatEntry");
        }

        if (heatEntry.getHeatResult() != null){
            throw new RuntimeException("No se puede actualizar un heatEntry conu heatResult asociado");
        }


        if (request.getStartPosicion() != null) {


            // Solo si cambia (para no considerarte "duplicado" a ti mismo)
            if (!request.getStartPosicion().equals(heatEntry.getStartPosition())) {

                // Duplicado con otro HeatEntry del mismo heat
                if (heatEntryRepository.existsByHeatIdAndStartPosition(heatEntry.getHeat().getId(), newPos)) {
                    throw new RuntimeException("No se puede asignar la misma posición inicial dentro de un heat");
                }

                // Aplicar cambio
                heatEntry.setStartPosition(request.getStartPosicion());
            }

            // Si es igual, no hacemos nada (update idempotente)
        }



        HeatEntry heatEntryUpdated = heatEntryRepository.save(heatEntry);

        return new HeatEntryResponse(
                heatEntryUpdated.getId(),
                heatEntryUpdated.getStartPosition(),
                heatEntryUpdated.getPilot().getId(),
                heatEntry.getHeat().getId(),
                null

        );

    }

    public void  deleteHeatEntry (Long id){

        HeatEntry heatEntry = heatEntryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HeatEntry no encontado"));

        if (heatEntry.getHeatResult() != null){
            throw new RuntimeException("No se puede borrar un HeatEntry con un heatResult asociado");
        }

        heatEntryRepository.delete(heatEntry);




    }









}
