package com.firesafe.firesafe360.service;

import com.firesafe.firesafe360.entity.Incident;
import org.springframework.stereotype.Service;

@Service
public class ResponsePlanService {

    public String generateResponsePlan(Incident incident) {

        String severity = incident.getSeverity();

        if ("HIGH".equalsIgnoreCase(severity)) {
            return "IMMEDIATE RESPONSE: Dispatch 3 fire engines and 15 firefighters. "
                    + "Evacuate the building and give highest priority to people at risk.";
        }

        if ("MEDIUM".equalsIgnoreCase(severity)) {
            return "PRIORITY RESPONSE: Dispatch 2 fire engines and 10 firefighters. "
                    + "Monitor the incident and begin controlled evacuation.";
        }

        return "NORMAL RESPONSE: Dispatch 1 fire engine and 5 firefighters. "
                + "Monitor the situation and provide initial fire control.";
    }
}