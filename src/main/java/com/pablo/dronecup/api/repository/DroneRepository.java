package com.pablo.dronecup.repository;

import com.pablo.dronecup.api.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, Long> {
}
