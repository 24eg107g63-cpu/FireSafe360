package com.firesafe.firesafe360.service;

import com.firesafe.firesafe360.entity.FireStation;
import com.firesafe.firesafe360.repository.FireStationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FireStationService {

    private final FireStationRepository fireStationRepository;

    public FireStationService(FireStationRepository fireStationRepository) {
        this.fireStationRepository = fireStationRepository;
    }

    public FireStation createFireStation(FireStation station) {
        return fireStationRepository.save(station);
    }

    public List<FireStation> getAllFireStations() {
        return fireStationRepository.findAll();
    }

    public Optional<FireStation> getFireStationById(Long id) {
        return fireStationRepository.findById(id);
    }

    public void deleteFireStation(Long id) {
        fireStationRepository.deleteById(id);
    }
}