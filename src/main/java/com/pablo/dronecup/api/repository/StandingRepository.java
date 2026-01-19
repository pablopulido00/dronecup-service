package com.pablo.dronecup.api.repository;

import com.pablo.dronecup.api.model.Standing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StandingRepository extends JpaRepository<Standing, Long> {

    List<Standing> findByChampionshipIdOrderByPointsDesc (Long championshipId);

    Optional<Standing> findByChampioshipAndPilotId(Long championshipId, Long pilotId);

    long countByChampionshipId (Long championshipId);



}
