package com.firesafe.firesafe360.controller;

import com.firesafe.firesafe360.entity.FireStation;
import com.firesafe.firesafe360.service.FireStationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fire-stations")
@CrossOrigin(origins = "*")
public class FireStationController {

    private final FireStationService fireStationService;

    public FireStationController(FireStationService fireStationService) {
        this.fireStationService = fireStationService;
    }

    @PostMapping
    public FireStation createFireStation(@RequestBody FireStation station) {
        return fireStationService.createFireStation(station);
    }

    @GetMapping
    public List<FireStation> getAllFireStations() {
        return fireStationService.getAllFireStations();
    }

    @GetMapping("/{id}")
    public FireStation getFireStationById(@PathVariable Long id) {
        return fireStationService.getFireStationById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public String deleteFireStation(@PathVariable Long id) {
        fireStationService.deleteFireStation(id);
        return "Fire station deleted successfully";
    }
}