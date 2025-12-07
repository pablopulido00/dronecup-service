package com.pablo.dronecup.repository;

import com.pablo.dronecup.api.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
