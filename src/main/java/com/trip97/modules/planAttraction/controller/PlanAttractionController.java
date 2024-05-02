package com.trip97.modules.planAttraction.controller;

import com.trip97.modules.planAttraction.model.PlanAttraction;
import com.trip97.modules.planAttraction.model.service.PlanAttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/group/{groupId}/plan/{planId}/attraction")
@RequiredArgsConstructor
public class PlanAttractionController {

    private final PlanAttractionService planAttractionService;

    @GetMapping
    public ResponseEntity<?> getPlanAttractions(@PathVariable int groupId, @PathVariable int planId) {
        List<PlanAttraction> list = planAttractionService.selectPlanAttractions(planId);
        if (list != null && !list.isEmpty()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(list);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/{attractionId}")
    public ResponseEntity<?> getPlanAttraction(@PathVariable int groupId, @PathVariable("attractionId") int planAttractionId) {
        PlanAttraction planAttraction = planAttractionService.selectPlanAttraction(planAttractionId);
        if (planAttraction != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(planAttraction);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> registerPlanAttraction(@PathVariable int planId, @RequestParam int attractionId) {
        planAttractionService.registerPlanAttraction(planId, attractionId);
        List<PlanAttraction> list = planAttractionService.selectPlanAttractions(planId);
        if (list != null && !list.isEmpty()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(list);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PutMapping("/{attractionId}")
    public ResponseEntity<?> editPlanAttraction(@PathVariable int groupId, @PathVariable int attractionId, @RequestBody PlanAttraction planAttraction) {
        planAttractionService.editPlanAttraction(planAttraction);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{attractionId}")
    public ResponseEntity<?> removePlanAttraction(@PathVariable int groupId, @PathVariable("attractionId") int planAttractionId) {
        planAttractionService.removePlanAttraction(planAttractionId);
        return ResponseEntity.noContent().build();
    }
}
