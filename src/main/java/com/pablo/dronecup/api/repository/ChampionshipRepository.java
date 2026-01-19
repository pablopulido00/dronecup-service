package com.pablo.dronecup.api.repository;

import com.pablo.dronecup.api.model.Championship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChampionshipRepository extends JpaRepository <Championship, Long>{

    Optional<Championship>  findTopByOrderByIdAsc();
}
