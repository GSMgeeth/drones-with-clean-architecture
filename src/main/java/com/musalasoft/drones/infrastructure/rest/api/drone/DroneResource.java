package com.musalasoft.drones.infrastructure.rest.api.drone;

import com.musalasoft.drones.infrastructure.rest.api.drone.dto.DroneBucketResponseDTO;
import com.musalasoft.drones.infrastructure.rest.api.drone.dto.DroneResponseDTO;
import com.musalasoft.drones.infrastructure.rest.api.drone.dto.RegisterDroneRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/drone")
public class DroneResource {
    private final RegisterDroneController registerDroneController;
    private final GetDroneController getDroneController;

    public DroneResource(RegisterDroneController registerDroneController, GetDroneController getDroneController) {
        this.registerDroneController = registerDroneController;
        this.getDroneController = getDroneController;
    }

    @PostMapping("/register")
    public ResponseEntity<DroneResponseDTO> registerDrone(@RequestBody final RegisterDroneRequestDTO droneRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(DroneResponseDTO.from(registerDroneController.registerDrone(droneRequestDTO.toDrone())));
    }

    @GetMapping("/by-serial-number/{serialNumber}")
    public ResponseEntity<DroneResponseDTO> getDroneBySerialNumber(@PathVariable final String serialNumber) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(DroneResponseDTO.from(getDroneController.getDroneBySerialNumber(serialNumber)));
    }

    @GetMapping("/by-state/{droneState}")
    public ResponseEntity<List<DroneResponseDTO>> getDroneByState(@PathVariable final String droneState) {
        final List<DroneResponseDTO> droneResponseList = getDroneController.getDronesByState(droneState).stream()
                .map(DroneResponseDTO::from).toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(droneResponseList);
    }

    @GetMapping("/battery/level/by-serial-number/{serialNumber}")
    public ResponseEntity<Double> getDroneBatteryLevelBySerialNumber(@PathVariable final String serialNumber) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getDroneController.getDroneBatteryLevelBySerialNumber(serialNumber));
    }

    @GetMapping("/bucket/by-id/{droneId}")
    public ResponseEntity<DroneBucketResponseDTO> getDroneBucketById(@PathVariable final Long droneId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(DroneBucketResponseDTO.from(getDroneController.getDroneBucketByDrone(droneId)));
    }
}
