package com.musalasoft.drones.infrastructure.rest.api.drone;

import com.musalasoft.drones.infrastructure.rest.api.drone.dto.DroneResponseDTO;
import com.musalasoft.drones.infrastructure.rest.api.drone.dto.RegisterDroneRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drone")
public class DroneResource {
    private final DroneController droneController;

    public DroneResource(DroneController droneController) {
        this.droneController = droneController;
    }

    @PostMapping("/register")
    public ResponseEntity<DroneResponseDTO> registerDrone(@RequestBody final RegisterDroneRequestDTO droneRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(DroneResponseDTO.from(droneController.registerDrone(droneRequestDTO.toDrone())));
    }
}
