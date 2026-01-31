package com.pablo.dronecup.api.service;


import com.pablo.dronecup.api.dto.HeatResultCreateRequest;
import com.pablo.dronecup.api.dto.HeatResultUpdateRequest;
import com.pablo.dronecup.api.exception.ConflictException;
import com.pablo.dronecup.api.model.Heat;
import com.pablo.dronecup.api.model.HeatEntry;
import com.pablo.dronecup.api.model.HeatResult;
import com.pablo.dronecup.api.repository.HeatEntryRepository;
import com.pablo.dronecup.api.repository.HeatResultRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HeatResultServiceTest {

    @Mock
    private HeatResultRepository heatResultRepository;

    @Mock
    private HeatEntryRepository heatEntryRepository;

    @Mock
    private StandingService standingService;

    @InjectMocks
    private HeatResultService heatResultService;


    @Test
    void createHeatResult_ok_saves_and_recalculates() {
        // Arrange
        Long heatEntryId = 50L;
        Long heatId = 99L;

        HeatResultCreateRequest request = new HeatResultCreateRequest();
        request.setHeatEntryId(heatEntryId);
        request.setPosition(1);
        request.setBestLapTime(120D);
        request.setTotalTime(400D);
        request.setPenalties(0);

        Heat heat = new Heat();
        heat.setId(heatId);

        HeatEntry heatEntry = new HeatEntry();
        heatEntry.setId(heatEntryId);
        heatEntry.setHeat(heat);
        heatEntry.setHeatResult(null);


        when(heatEntryRepository.findById(heatEntryId)).thenReturn(Optional.of(heatEntry));
        when(heatResultRepository.existsByHeatEntryHeatIdAndPosition(heatId, 1)).thenReturn(false);

        when(heatResultRepository.save(any(HeatResult.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        ArgumentCaptor<HeatResult> captor = ArgumentCaptor.forClass(HeatResult.class);


        heatResultService.createHeatResult(request);

        verify(heatResultRepository).save(captor.capture());
        HeatResult saved = captor.getValue();

        assertEquals(1, saved.getPosition());
        assertEquals(120, saved.getBestLapTime());
        assertEquals(400, saved.getTotalTime());
        assertEquals(0, saved.getPenalties());
        assertSame(heatEntry, saved.getHeatEntry());

        verify(standingService).recalculateStandings();
    }

    @Test
    void createHeatResult_whenHeatEntryAlreadyHasResult_throwsConflict() {

        Long heatEntryId = 50L;

        HeatResultCreateRequest request = new HeatResultCreateRequest();
        request.setHeatEntryId(heatEntryId);
        request.setPosition(1);
        request.setBestLapTime(120D);
        request.setTotalTime(400D);
        request.setPenalties(0);

        HeatEntry heatEntry = new HeatEntry();
        heatEntry.setId(heatEntryId);


        HeatResult existingResult = new HeatResult();
        heatEntry.setHeatResult(existingResult);

        when(heatEntryRepository.findById(heatEntryId)).thenReturn(Optional.of(heatEntry));


        assertThrows(ConflictException.class,
                () -> heatResultService.createHeatResult(request));


        verify(heatEntryRepository).findById(heatEntryId);

        verify(heatResultRepository, never()).save(any(HeatResult.class));
        verify(standingService, never()).recalculateStandings();


        verify(heatResultRepository, never())
                .existsByHeatEntryHeatIdAndPosition(anyLong(), anyInt());
    }


    @Test
    void updateHeatResult_whenPositionChangesToExistingPosition_throwsConflict() {

        Long heatResultId = 100L;
        Long heatId = 99L;

        Heat heat = new Heat();
        heat.setId(heatId);

        HeatEntry heatEntry = new HeatEntry();
        heatEntry.setId(50L);
        heatEntry.setHeat(heat);

        HeatResult heatResult = new HeatResult();
        heatResult.setId(heatResultId);
        heatResult.setHeatEntry(heatEntry);
        heatResult.setPosition(1);
        heatResult.setBestLapTime(120D);
        heatResult.setTotalTime(400D);
        heatResult.setPenalties(0);

        when(heatResultRepository.findById(heatResultId)).thenReturn(Optional.of(heatResult));

        HeatResultUpdateRequest request = new HeatResultUpdateRequest();
        request.setPosition(2);


        when(heatResultRepository.existsByHeatEntryHeatIdAndPosition(heatId, 2)).thenReturn(true);


        assertThrows(ConflictException.class,
                () -> heatResultService.updateHeatResult(heatResultId, request));


        verify(heatResultRepository).findById(heatResultId);
        verify(heatResultRepository).existsByHeatEntryHeatIdAndPosition(heatId, 2);

        verify(heatResultRepository, never()).save(any(HeatResult.class));
        verify(standingService, never()).recalculateStandings();
    }


    @Test
    void deleteHeatResult_ok_deletes_and_recalculates() {

        Long heatResultId = 100L;

        HeatResult heatResult = new HeatResult();
        heatResult.setId(heatResultId);

        when(heatResultRepository.findById(heatResultId))
                .thenReturn(Optional.of(heatResult));


        heatResultService.deleteHeatResult(heatResultId);



        verify(heatResultRepository).findById(heatResultId);
        verify(heatResultRepository).delete(heatResult);
        verify(standingService).recalculateStandings();

    }


}
