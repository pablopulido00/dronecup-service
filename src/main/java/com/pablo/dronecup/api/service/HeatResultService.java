package com.pablo.dronecup.api.service;

import com.pablo.dronecup.api.dto.HeatResultCreateRequest;
import com.pablo.dronecup.api.dto.HeatResultResponse;
import com.pablo.dronecup.api.dto.HeatResultUpdateRequest;
import com.pablo.dronecup.api.exception.BadRequestException;
import com.pablo.dronecup.api.exception.ConflictException;
import com.pablo.dronecup.api.exception.NotFoundException;
import com.pablo.dronecup.api.model.HeatEntry;
import com.pablo.dronecup.api.model.HeatResult;
import com.pablo.dronecup.api.repository.HeatEntryRepository;
import com.pablo.dronecup.api.repository.HeatResultRepository;
import org.springframework.stereotype.Service;

@Service
public class HeatResultService {

    private final HeatResultRepository heatResultRepository;
    private final HeatEntryRepository heatEntryRepository;
    private final StandingService standingService;

    public HeatResultService(HeatResultRepository heatResultRepository, HeatEntryRepository heatEntryRepository, StandingService standingService) {
        this.heatResultRepository = heatResultRepository;
        this.heatEntryRepository = heatEntryRepository;
        this.standingService = standingService;
    }

    public HeatResultResponse createHeatResult(HeatResultCreateRequest request) {

        HeatEntry heatEntry = heatEntryRepository.findById(request.getHeatEntryId())
                .orElseThrow(() -> new NotFoundException("HeatEntry no encontrado"));

        if (heatEntry.getHeatResult() != null) {
            throw new ConflictException("Ese heatEntry ya tiene resultado");
        }

        if (request.getTotalTime() < request.getBestLapTime()) {
            throw new BadRequestException("El tiempo total no puede ser menor que la mejor vuelta");
        }

        if (heatResultRepository.existsByHeatEntryHeatIdAndPosition(
                heatEntry.getHeat().getId(), request.getPosition())) {
            throw new ConflictException(
                    "Ya existe un resultado con la posicion " + request.getPosition() + " en ese heat"
            );
        }

        HeatResult heatResult = new HeatResult();

        heatResult.setPosition(request.getPosition());
        heatResult.setBestLapTime(request.getBestLapTime());
        heatResult.setTotalTime(request.getTotalTime());
        heatResult.setPenalties(request.getPenalties());
        heatResult.setHeatEntry(heatEntry);

        HeatResult heatResultSaved = heatResultRepository.save(heatResult);

        standingService.recalculateStandings();

        return new HeatResultResponse(
                heatResultSaved.getId(),
                heatResultSaved.getPosition(),
                heatResultSaved.getBestLapTime(),
                heatResultSaved.getTotalTime(),
                heatResultSaved.getPenalties(),
                heatResultSaved.getHeatEntry().getId()
        );
    }

    public HeatResultResponse updateHeatResult(Long id, HeatResultUpdateRequest request) {

        HeatResult heatResult = heatResultRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("HeatResult no encontrado"));


        if (request.getBestLapTime() != null) {
            heatResult.setBestLapTime(request.getBestLapTime());
        }

        if (request.getTotalTime() != null) {
            heatResult.setTotalTime(request.getTotalTime());
        }

        if (request.getPenalties() != null) {
            heatResult.setPenalties(request.getPenalties());
        }

        if (request.getPosition() != null) {

            if (!heatResult.getPosition().equals(request.getPosition())) {
                if (heatResultRepository.existsByHeatEntryHeatIdAndPosition(
                        heatResult.getHeatEntry().getHeat().getId(),
                        request.getPosition())) {
                    throw new ConflictException("No se puede tener la misma posicion en un heat");
                }

                heatResult.setPosition(request.getPosition());
            }
        }

        if (heatResult.getTotalTime() < heatResult.getBestLapTime()) {
            throw new BadRequestException("El tiempo total no puede ser menor que la mejor vuelta");
        }

        HeatResult heatResultUpdated = heatResultRepository.save(heatResult);

        standingService.recalculateStandings();

        return new HeatResultResponse(
                heatResultUpdated.getId(),
                heatResultUpdated.getPosition(),
                heatResultUpdated.getBestLapTime(),
                heatResultUpdated.getTotalTime(),
                heatResultUpdated.getPenalties(),
                heatResultUpdated.getHeatEntry().getId()
        );
    }

    public void deleteHeatResult(Long id) {

        HeatResult heatResult = heatResultRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("HeatResult no encontrado"));

        heatResultRepository.delete(heatResult);

        standingService.recalculateStandings();
    }
}
