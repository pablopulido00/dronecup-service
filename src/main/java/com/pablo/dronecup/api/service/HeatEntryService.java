package com.pablo.dronecup.api.service;

import com.pablo.dronecup.api.dto.HeatEntryCreateRequest;
import com.pablo.dronecup.api.dto.HeatEntryResponse;
import com.pablo.dronecup.api.dto.HeatEntryUpdateRequest;
import com.pablo.dronecup.api.dto.HeatResultSummary;
import com.pablo.dronecup.api.exception.ConflictException;
import com.pablo.dronecup.api.exception.NotFoundException;
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

    public HeatEntryResponse createHeatEntry(HeatEntryCreateRequest request) {

        Pilot pilot = pilotRespository.findById(request.getPilotId())
                .orElseThrow(() -> new NotFoundException("Pilot con id=" + request.getPilotId() + "no existe"));

        Heat heat = heatRepository.findById(request.getHeatId())
                .orElseThrow(() -> new NotFoundException("Heat con id=" + request.getHeatId() + "no existe"));

        if (heatEntryRepository.existsByHeatIdAndPilotId(heat.getId(), pilot.getId())) {
            throw new ConflictException("El piloto " + pilot.getName() + " ya esta inscrito en ese heat");
        }

        if (heatEntryRepository.existsByHeatIdAndStartPosition(heat.getId(), request.getStartPosicion())) {
            throw new ConflictException("La posicion de salida" + request.getStartPosicion() + " ya esta ocupada");
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
                .orElseThrow(() -> new NotFoundException("HeatEntry con id=" + id + "no existe"));

        if (heatEntry.getHeatResult() != null) {
            throw new ConflictException("No se puede actualizar un heatEntry con u heatResult asociado");
        }

        if (request.getStartPosicion() != null) {

            if (!request.getStartPosicion().equals(heatEntry.getStartPosition())) {

                if (heatEntryRepository.existsByHeatIdAndStartPosition(
                        heatEntry.getHeat().getId(),
                        request.getStartPosicion()
                )) {
                    throw new ConflictException("No se puede asignar la misma posición inicial dentro de un heat");
                }

                heatEntry.setStartPosition(request.getStartPosicion());
            }
        }

        HeatEntry heatEntryUpdated = heatEntryRepository.save(heatEntry);

        return new HeatEntryResponse(
                heatEntryUpdated.getId(),
                heatEntryUpdated.getStartPosition(),
                heatEntryUpdated.getPilot().getId(),
                heatEntryUpdated.getHeat().getId(),
                heatEntryUpdated.getHeatResult() == null
                        ? null
                        : new HeatResultSummary(
                        heatEntryUpdated.getHeatResult().getPosition(),
                        heatEntryUpdated.getHeatResult().getBestLapTime(),
                        heatEntryUpdated.getHeatResult().getTotalTime(),
                        heatEntryUpdated.getHeatResult().getPenalties()
                )
        );
    }


    public void deleteHeatEntry(Long id) {

        HeatEntry heatEntry = heatEntryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("HeatEntry con id=" + id + "no existe"));

        if (heatEntry.getHeatResult() != null) {
            throw new ConflictException("No se puede borrar un HeatEntry con un heatResult asociado");
        }

        heatEntryRepository.delete(heatEntry);
    }
}
