package com.pablo.dronecup.api.service;

import com.pablo.dronecup.api.dto.StandingResponse;
import com.pablo.dronecup.api.exception.ConflictException;
import com.pablo.dronecup.api.model.*;
import com.pablo.dronecup.api.repository.ChampionshipRepository;
import com.pablo.dronecup.api.repository.HeatResultRepository;
import com.pablo.dronecup.api.repository.PilotRepository;
import com.pablo.dronecup.api.repository.StandingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StandingServiceTest {

    @Mock
    private StandingRepository standingRepository;

    @Mock
    private ChampionshipRepository championshipRepository;

    @Mock
    private PilotRepository pilotRepository;

    @Mock
    private HeatResultRepository heatResultRepository;

    @InjectMocks
    private StandingService standingService;



    @Test
    void getStandings_ok_map_to_response(){

        Championship ch = new Championship();
        ch.setId(1L);

        Pilot pilot = new Pilot();
        pilot.setId(10L);
        pilot.setName("Pilot 1");

        Standing standing = new Standing();
        standing.setId(100L);
        standing.setPilot(pilot);
        standing.setPoints(15);

        when(championshipRepository.findAll()).thenReturn(List.of(ch));
        when(standingRepository.findAllByOrderByPointsDesc()).thenReturn(List.of(standing));

        List<StandingResponse> res = standingService.getStandings();

        assertNotNull(res);
        assertEquals(1, res.size());

        StandingResponse dto = res.get(0);

        assertEquals(100L, dto.getId());
        assertEquals(10L, dto.getPilotId());
        assertEquals("Pilot 1", dto.getPilotName());
        assertEquals(15, dto.getPoints());

        verify(championshipRepository).findAll();
        verify(standingRepository).findAllByOrderByPointsDesc();

    }

    @Test
    void recalculateStandings_ok_sums_points_and_saves_for_each_pilot(){

        Championship ch = new Championship();
        ch.setId(1L);

        when(championshipRepository.findAll()).thenReturn(List.of(ch));

        Pilot p1 = new Pilot(); p1.setId(10L);
        Pilot p2 = new Pilot(); p2.setId(20L);

        when(pilotRepository.findAll()).thenReturn(List.of(p1, p2));


        HeatEntry he1 = mock(HeatEntry.class);
        when(he1.getPilot()).thenReturn(p1);

        HeatEntry he2 = mock(HeatEntry.class);
        when(he2.getPilot()).thenReturn(p1);

        HeatEntry he3 = mock(HeatEntry.class);
        when(he3.getPilot()).thenReturn(p2);

        HeatResult r1 = new HeatResult();
        r1.setHeatEntry(he1);
        r1.setPosition(1);

        HeatResult r2 = new HeatResult();
        r2.setHeatEntry(he2);
        r2.setPosition(2);

        HeatResult r3 = new HeatResult();
        r3.setHeatEntry(he3);
        r3.setPosition(3);

        when(heatResultRepository.findAll()).thenReturn(List.of(r1, r2, r3));

        when(standingRepository.findByChampionshipIdAndPilotId(eq(1L), anyLong())).thenReturn(Optional.empty());

        ArgumentCaptor<Standing> captor = ArgumentCaptor.forClass(Standing.class);


        standingService.recalculateStandings();


        verify(standingRepository, times(2)).save(captor.capture());
        List<Standing> saved = captor.getAllValues();

        Standing sP1 = saved.stream()
                .filter(s -> s.getPilot().getId().equals(10L))
                .findFirst()
                .orElseThrow();

        Standing sP2 = saved.stream()
                .filter(s -> s.getPilot().getId().equals(20L))
                .findFirst()
                .orElseThrow();

        assertEquals(1L, sP1.getChampionship().getId());
        assertEquals(1L, sP2.getChampionship().getId());

        assertTrue(sP1.getPoints() > 0);
        assertTrue(sP1.getPoints() > sP2.getPoints());
    }



    @Test
    void recalculateStandings_whenStandingExists_updates_existing() {
        // Arrange
        Championship championship = new Championship();
        championship.setId(1L);
        when(championshipRepository.findAll()).thenReturn(List.of(championship));

        Pilot p1 = new Pilot(); p1.setId(10L);
        Pilot p2 = new Pilot(); p2.setId(20L);
        when(pilotRepository.findAll()).thenReturn(List.of(p1, p2));

        HeatEntry he1 = mock(HeatEntry.class);
        when(he1.getPilot()).thenReturn(p1);

        HeatEntry he2 = mock(HeatEntry.class);
        when(he2.getPilot()).thenReturn(p1);

        HeatEntry he3 = mock(HeatEntry.class);
        when(he3.getPilot()).thenReturn(p2);

        HeatResult r1 = new HeatResult();
        r1.setHeatEntry(he1);
        r1.setPosition(1);

        HeatResult r2 = new HeatResult();
        r2.setHeatEntry(he2);
        r2.setPosition(2);

        HeatResult r3 = new HeatResult();
        r3.setHeatEntry(he3);
        r3.setPosition(3);

        when(heatResultRepository.findAll()).thenReturn(List.of(r1, r2, r3));


        Standing existingP1 = new Standing();
        existingP1.setId(999L);
        existingP1.setPilot(p1);
        existingP1.setChampionship(championship);
        existingP1.setPoints(123);


        when(standingRepository.findByChampionshipIdAndPilotId(1L, 10L))
                .thenReturn(Optional.of(existingP1));
        when(standingRepository.findByChampionshipIdAndPilotId(1L, 20L))
                .thenReturn(Optional.empty());

        ArgumentCaptor<Standing> captor = ArgumentCaptor.forClass(Standing.class);


        standingService.recalculateStandings();


        verify(standingRepository, times(2)).save(captor.capture());
        List<Standing> saved = captor.getAllValues();

        Standing savedP1 = saved.stream()
                .filter(s -> s.getPilot().getId().equals(10L))
                .findFirst()
                .orElseThrow();


        assertSame(existingP1, savedP1);


        assertEquals(1L, savedP1.getChampionship().getId());
        assertEquals(10L, savedP1.getPilot().getId());


        assertTrue(savedP1.getPoints() > 0);
    }


    @Test
    void recalculateStandings_whenMoreThanOneChampionship_throwsConflict() {

        Championship c1 = new Championship();
        c1.setId(1L);

        Championship c2 = new Championship();
        c2.setId(2L);

        when(championshipRepository.findAll()).thenReturn(List.of(c1, c2));


        assertThrows(ConflictException.class,
                () -> standingService.recalculateStandings());


        verifyNoInteractions(pilotRepository);
        verifyNoInteractions(heatResultRepository);
        verifyNoInteractions(standingRepository);
    }


}
