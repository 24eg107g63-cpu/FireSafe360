package com.firesafe.firesafe360.repository;

import com.firesafe.firesafe360.entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
}