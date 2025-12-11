package com.pablo.dronecup.api.repository;

import com.pablo.dronecup.api.model.Pilot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PilotRepository extends JpaRepository<Pilot, Long> {
}
