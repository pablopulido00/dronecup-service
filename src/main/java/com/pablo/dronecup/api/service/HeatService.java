package com.pablo.dronecup.api.service;

import com.pablo.dronecup.api.dto.*;
import com.pablo.dronecup.api.model.Event;
import com.pablo.dronecup.api.model.Heat;
import com.pablo.dronecup.api.repository.EventRepository;
import com.pablo.dronecup.api.repository.HeatRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class HeatService {


    private final HeatRepository heatRepository;
    private final EventRepository eventRepository;

    public HeatService(HeatRepository heatRepository, EventRepository eventRepository) {
        this.heatRepository = heatRepository;
        this.eventRepository = eventRepository;
    }


    public HeatResponse createHeat (HeatCreateRequest request){

        Event event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new RuntimeException("Event no encontrado"));

        Heat heat = new Heat();

        heat.setName(request.getName());
        heat.setNumber(request.getNumber());
        heat.setEvent(event);

        Heat heatSaved = heatRepository.save(heat);

        return new HeatResponse (
                heatSaved.getId(),
                heatSaved.getName(),
                heatSaved.getNumber(),
                heatSaved.getEvent().getId(),
                List.of()

        );


    }


    public HeatResponse getHeatById (Long id){

        Heat heat = heatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Heat no encontrado"));

        return new HeatResponse(
                heat.getId(),
                heat.getName(),
                heat.getNumber(),
                heat.getEvent().getId(),
                heat.getHeatEntries().stream()
                        .map(heatEntry -> new HeatEntryResponse(
                                heatEntry.getId(),
                                heatEntry.getStartPosition(),
                                heatEntry.getPilot().getId(),
                                heatEntry.getHeat().getId(),
                                (heatEntry.getHeatResult() == null)
                                ? null
                                :
                                new HeatResultSummary(
                                        heatEntry.getHeatResult().getPosition(),
                                        heatEntry.getHeatResult().getBestLapTime(),
                                        heatEntry.getHeatResult().getTotalTime(),
                                        heatEntry.getHeatResult().getPenalties()
                                )
                        )).toList()


        );
    }

    public List<HeatResponse> getAllHeats (){

        return heatRepository.findAll().stream()
                .map(heat -> new HeatResponse(
                        heat.getId(),
                        heat.getName(),
                        heat.getNumber(),
                        heat.getEvent().getId(),
                        heat.getHeatEntries().stream()
                                .map(heatEntry -> new HeatEntryResponse(
                                        heatEntry.getId(),
                                        heatEntry.getStartPosition(),
                                        heatEntry.getPilot().getId(),
                                        heatEntry.getHeat().getId(),
                                        (heatEntry.getHeatResult() == null)
                                        ? null
                                        : new HeatResultSummary(
                                                heatEntry.getHeatResult().getPosition(),
                                                heatEntry.getHeatResult().getBestLapTime(),
                                                heatEntry.getHeatResult().getTotalTime(),
                                                heatEntry.getHeatResult().getPenalties()

                                        )
                                )).toList()
                )).toList();


    }


    public HeatResponse updateHeat (Long id, HeatUpdateRequest request){

        Heat heat = heatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Heat no encontrado"));

        if (request.getName() != null){
            heat.setName(request.getName());
        }

        if (request.getNumber() != null){
            heat.setNumber(request.getNumber());
        }

        if (request.getEventId() != null){
            throw new RuntimeException("No se puede cambiar el heat de un evento");
        }

        Heat heatUpdated = heatRepository.save(heat);

        return new HeatResponse(
                heatUpdated.getId(),
                heatUpdated.getName(),
                heatUpdated.getNumber(),
                heatUpdated.getEvent().getId(),
                heatUpdated.getHeatEntries().stream()
                        .map(heatEntry -> new HeatEntryResponse(
                                heatEntry.getId(),
                                heatEntry.getStartPosition(),
                                heatEntry.getPilot().getId(),
                                heatEntry.getHeat().getId(),
                                (heatEntry.getHeatResult() == null)
                                ? null
                                : new HeatResultSummary(
                                        heatEntry.getHeatResult().getPosition(),
                                        heatEntry.getHeatResult().getBestLapTime(),
                                        heatEntry.getHeatResult().getTotalTime(),
                                        heatEntry.getHeatResult().getPenalties()
                                )
                        )).toList()
        );

    }

    public void deleteHeat (Long id){

        Heat heat  = heatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Heat no encontrado"));

        if (!heat.getHeatEntries().isEmpty()){
            throw new RuntimeException("No se puede borrar un heat con heatEntries asociados");
        }

        heatRepository.delete(heat);


    }
}
