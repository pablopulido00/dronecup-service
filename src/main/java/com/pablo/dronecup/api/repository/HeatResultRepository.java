package com.pablo.dronecup.api.repository;

import com.pablo.dronecup.api.model.HeatResult;
import com.pablo.dronecup.api.model.Standing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeatResultRepository extends JpaRepository<HeatResult, Long> {

    boolean existsByHeatEntryHeatIdAndPosition(Long id , Integer Position);



}
