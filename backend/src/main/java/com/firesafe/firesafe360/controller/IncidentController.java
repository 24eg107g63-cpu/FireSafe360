package com.firesafe.firesafe360.controller;

import com.firesafe.firesafe360.entity.Incident;
import com.firesafe.firesafe360.service.IncidentService;
import com.firesafe.firesafe360.service.SeverityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
@CrossOrigin(origins = "*")
public class IncidentController {

    private final IncidentService incidentService;
    private final SeverityService severityService;

    public IncidentController(
            IncidentService incidentService,
            SeverityService severityService) {

        this.incidentService = incidentService;
        this.severityService = severityService;
    }

    @PostMapping
    public Incident createIncident(@RequestBody Incident incident) {

        // Automatically calculate severity
        String severity = severityService.calculateSeverity(incident);
        incident.setSeverity(severity);

        return incidentService.createIncident(incident);
    }

    @GetMapping
    public List<Incident> getAllIncidents() {
        return incidentService.getAllIncidents();
    }

    @GetMapping("/{id}")
    public Incident getIncidentById(@PathVariable Long id) {
        return incidentService.getIncidentById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public String deleteIncident(@PathVariable Long id) {
        incidentService.deleteIncident(id);
        return "Incident deleted successfully";
    }
}