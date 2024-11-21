package com.example.realestate.payment.controller;

import com.example.realestate.payment.dto.PlanDTO;
import com.example.realestate.payment.service.PlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/plan")
public class PlanController {
    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @PostMapping
    public ResponseEntity<PlanDTO> createOrUpdate(@RequestBody PlanDTO dto) {
        try {
            PlanDTO result = planService.createOrUpdate(dto);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            // Si une exception est lancée, retournez une erreur appropriée
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }    }

    @GetMapping("/by-name")
    public ResponseEntity<PlanDTO> getPlanByName(@RequestParam String name) {
        return ResponseEntity.ok(planService.getPlanByName(name));
    }

    @GetMapping
    public ResponseEntity<List<PlanDTO>> getAllPlans() {
        return ResponseEntity.ok(planService.planList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanDTO> getPlanById(@PathVariable String id) {
        return ResponseEntity.ok(planService.findById(Long.valueOf(id)));
    }


    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable String id) {
        return ResponseEntity.ok(planService.existsById(Long.valueOf(id)));
    }
}
