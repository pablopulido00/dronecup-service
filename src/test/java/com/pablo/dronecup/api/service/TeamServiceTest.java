package com.pablo.dronecup.api.service;


import com.pablo.dronecup.api.dto.TeamCreateRequest;
import com.pablo.dronecup.api.dto.TeamResponse;
import com.pablo.dronecup.api.dto.TeamUpdateRequest;
import com.pablo.dronecup.api.exception.ConflictException;
import com.pablo.dronecup.api.exception.NotFoundException;
import com.pablo.dronecup.api.model.Pilot;
import com.pablo.dronecup.api.model.Team;
import com.pablo.dronecup.api.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    TeamService teamService;


    @Test
    void getTeamById_ok_returns_response(){
        Long id = 1000L;

        Team team = new Team();
        team.setId(id);
        team.setName("Team A");
        team.setCountry("Spain");
        team.setPilots(List.of());

        when(teamRepository.findById(id)).thenReturn(Optional.of(team));

        // Act
        TeamResponse res = teamService.getTeamById(id);


        assertNotNull(res);
        assertEquals(id, res.getId());
        assertEquals("Team A", res.getName());
        assertEquals("Spain", res.getCountry());
        assertNotNull(res.getPilots());
        assertTrue(res.getPilots().isEmpty());


        verify(teamRepository).findById(id);

    }

    @Test
    void getTeamById_notFound_throws() {

        Long id = 999L;

        when(teamRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> teamService.getTeamById(id));

        verify(teamRepository).findById(id);

    }

    @Test
    void createTeam_ok_saves_and_returns_response(){

        TeamCreateRequest req = new TeamCreateRequest();
        req.setName("Team A");
        req.setCountry("Spain");

        Team savedTeam = new Team();
        savedTeam.setId(1000L);
        savedTeam.setName("Team A");
        savedTeam.setCountry("Spain");
        savedTeam.setPilots(List.of());

        when(teamRepository.save(any(Team.class))).thenReturn(savedTeam);

        TeamResponse res = teamService.createTeam(req);

        assertNotNull(res);
        assertEquals(savedTeam.getId(), res.getId());
        assertEquals(savedTeam.getName(), res.getName());
        assertEquals(savedTeam.getCountry(), res.getCountry());
        assertNotNull(res.getPilots());
        assertTrue(res.getPilots().isEmpty());


        verify(teamRepository).save(any(Team.class));


    }

    @Test
    void deleteTeam_withPilots_throwsConflict_and_doesNotDelete(){

            Long id = 1000L;

            Team team = new Team();
            team.setId(id);
            team.setName("Team A");
            team.setCountry("Spain");

            Pilot pilot = new Pilot();
            pilot.setId(1L);
            pilot.setName("Pilot 1");

            team.setPilots(List.of(pilot));

            when(teamRepository.findById(id)).thenReturn(Optional.of(team));

            assertThrows(ConflictException.class, () -> teamService.deleteTeam(id));

            verify(teamRepository).findById(id);
            verify(teamRepository, never()).delete(any(Team.class));


    }

    @Test
    void deleteTeam_withoutPilots_deletes(){

        Long id  = 1000L;

        Team team = new Team();
        team.setId(id);
        team.setPilots(List.of());

        when(teamRepository.findById(id)).thenReturn(Optional.of(team));

        assertDoesNotThrow(() -> teamService.deleteTeam(id));

        verify(teamRepository).findById(id);
        verify(teamRepository, times(1)).delete(team);
    }

    @Test
    void updateTeam_partialUpdate_updates_only_nonNull_fields_and_returns_response(){

        Long id = 1000L;

        Team existing = new Team();
        existing.setId(id);
        existing.setName("Old Name");
        existing.setCountry("Old Country");
        existing.setPilots(List.of());

        TeamUpdateRequest req = new TeamUpdateRequest();
        req.setName("New Name");
        req.setCountry(null);

        when(teamRepository.findById(id)).thenReturn(Optional.of(existing));

        when(teamRepository.save(any(Team.class))).thenAnswer(inv -> inv.getArgument(0));

        TeamResponse res = teamService.updateTeam(id, req);

        assertNotNull(res);
        assertEquals(id, res.getId());
        assertEquals("New Name", res.getName());
        assertEquals("Old Country", res.getCountry());
        assertNotNull(res.getPilots());
        assertTrue(res.getPilots().isEmpty());

        verify(teamRepository, times(1)).findById(id);
        verify(teamRepository, times (1)).save(any(Team.class));



    }

}
