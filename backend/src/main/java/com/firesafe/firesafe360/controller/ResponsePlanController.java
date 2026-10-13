package com.firesafe.firesafe360.controller;

import com.firesafe.firesafe360.entity.Incident;
import com.firesafe.firesafe360.service.IncidentService;
import com.firesafe.firesafe360.service.ResponsePlanService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/response-plan")
@CrossOrigin(origins = "*")
public class ResponsePlanController {

    private final IncidentService incidentService;
    private final ResponsePlanService responsePlanService;

    public ResponsePlanController(
            IncidentService incidentService,
            ResponsePlanService responsePlanService) {

        this.incidentService = incidentService;
        this.responsePlanService = responsePlanService;
    }

    @GetMapping("/{incidentId}")
    public String generateResponsePlan(@PathVariable Long incidentId) {

        Incident incident = incidentService
                .getIncidentById(incidentId)
                .orElse(null);

        if (incident == null) {
            return "Incident not found";
        }

        return responsePlanService.generateResponsePlan(incident);
    }
}
