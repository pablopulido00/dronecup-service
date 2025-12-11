package com.pablo.dronecup.api.repository;

import com.pablo.dronecup.api.model.Championship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionshipRepository extends JpaRepository <Championship, Long>{
}
