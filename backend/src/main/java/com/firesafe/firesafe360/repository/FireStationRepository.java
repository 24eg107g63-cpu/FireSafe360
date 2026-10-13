package com.firesafe.firesafe360.repository;

import com.firesafe.firesafe360.entity.FireStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FireStationRepository extends JpaRepository<FireStation, Long> {
}