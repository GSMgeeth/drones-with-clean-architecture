package com.company.drones.infrastructure.rest.api.drone;

import com.company.drones.infrastructure.rest.api.drone.dto.RegisterDroneRequestDTO;
import com.company.drones.infrastructure.rest.api.drone.dto.DroneBucketResponseDTO;
import com.company.drones.infrastructure.rest.api.drone.dto.DroneResponseDTO;
import com.company.drones.infrastructure.rest.api.drone.dto.LoadDroneBucketRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/drone")
public class DroneResource {
    private final RegisterDroneController registerDroneController;
    private final GetDroneController getDroneController;
    private final LoadDroneController loadDroneController;

    public DroneResource(RegisterDroneController registerDroneController, GetDroneController getDroneController, LoadDroneController loadDroneController) {
        this.registerDroneController = registerDroneController;
        this.getDroneController = getDroneController;
        this.loadDroneController = loadDroneController;
    }

    @PostMapping("/register")
    public ResponseEntity<DroneResponseDTO> registerDrone(@RequestBody final RegisterDroneRequestDTO droneRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(DroneResponseDTO.from(registerDroneController.registerDrone(
                        Objects.requireNonNull(droneRequestDTO).toDrone())));
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
                .body(DroneBucketResponseDTO.from(loadDroneController.getDroneBucketByDrone(droneId)));
    }

    @PutMapping("/bucket")
    public ResponseEntity<DroneBucketResponseDTO> loadDroneWithItems(@RequestBody final LoadDroneBucketRequestDTO loadDroneBucketRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(DroneBucketResponseDTO.from(loadDroneController.loadDroneWithItems(
                        Objects.requireNonNull(loadDroneBucketRequestDTO).toDroneBucket())));
    }
}
