package com.firesafe.firesafe360.service;

import com.firesafe.firesafe360.entity.Incident;
import org.springframework.stereotype.Service;

@Service
public class SeverityService {

    public String calculateSeverity(Incident incident) {

        int score = 0;

        // People at risk
        if (incident.getPeopleAtRisk() >= 50) {
            score += 4;
        } else if (incident.getPeopleAtRisk() >= 20) {
            score += 3;
        } else if (incident.getPeopleAtRisk() >= 5) {
            score += 2;
        } else {
            score += 1;
        }

        // Number of floors
        if (incident.getFloors() >= 5) {
            score += 3;
        } else if (incident.getFloors() >= 3) {
            score += 2;
        } else {
            score += 1;
        }

        // Fire type
        if ("Chemical".equalsIgnoreCase(incident.getFireType())
                || "Gas".equalsIgnoreCase(incident.getFireType())) {
            score += 3;
        } else if ("Electrical".equalsIgnoreCase(incident.getFireType())) {
            score += 2;
        } else {
            score += 1;
        }

        // Building type
        if ("Industrial".equalsIgnoreCase(incident.getBuildingType())
                || "Hospital".equalsIgnoreCase(incident.getBuildingType())) {
            score += 3;
        } else if ("Commercial".equalsIgnoreCase(incident.getBuildingType())) {
            score += 2;
        } else {
            score += 1;
        }

        // Final severity
        if (score >= 10) {
            return "HIGH";
        } else if (score >= 7) {
            return "MEDIUM";
        } else {
            return "LOW";
        }
    }
}