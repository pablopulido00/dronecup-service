package com.pablo.dronecup.api.service;

import com.pablo.dronecup.api.dto.DroneCreateRequest;
import com.pablo.dronecup.api.dto.DroneResponse;
import com.pablo.dronecup.api.dto.DroneUpdateRequest;
import com.pablo.dronecup.api.model.Drone;
import com.pablo.dronecup.api.repository.DroneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DroneService {

    private final DroneRepository droneRepository;

    public DroneService(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    public DroneResponse createDrone(DroneCreateRequest request) {

        Drone drone = new Drone();
        drone.setModel(request.getModel());
        drone.setManufacturer(request.getManufacturer());

        Drone droneSaved = droneRepository.save(drone);

        return new DroneResponse(
                droneSaved.getId(),
                droneSaved.getModel(),
                droneSaved.getManufacturer()
        );
    }

    public DroneResponse getDroneById(Long id) {

        Drone drone = droneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Drone no encontrado"));

        return new DroneResponse(
                drone.getId(),
                drone.getModel(),
                drone.getManufacturer()
        );
    }

    public List<DroneResponse> getAllDrones() {
        return droneRepository.findAll().stream()
                .map(drone -> new DroneResponse(
                        drone.getId(),
                        drone.getModel(),
                        drone.getManufacturer()
                ))
                .toList();
    }

    public DroneResponse updateDrone(Long id, DroneUpdateRequest request) {

        Drone drone = droneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Drone no encontrado"));

        if (request.getModel() != null) {
            drone.setModel(request.getModel());
        }

        if (request.getManufacturer() != null) {
            drone.setManufacturer(request.getManufacturer());
        }

        Drone droneUpdated = droneRepository.save(drone);

        return new DroneResponse(
                droneUpdated.getId(),
                droneUpdated.getModel(),
                droneUpdated.getManufacturer()
        );
    }

    public void deleteDrone(Long id) {

        Drone drone = droneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Drone no encontrado"));

        if (drone.getPilot() != null) {
            throw new RuntimeException("No se puede eliminar un dron asignado a un piloto");
        }

        droneRepository.delete(drone);
    }
}
