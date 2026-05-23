// 2. PlanController.java
package com.study.planner.controller;

import com.study.planner.dto.PlanDto;
import com.study.planner.service.PlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/plans")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<?> createPlan(@PathVariable Long userId, @RequestBody PlanDto dto) {
        return ResponseEntity.ok(planService.createPlan(userId, dto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getPlans(@PathVariable Long userId) {
        return ResponseEntity.ok(planService.getPlansByUserId(userId));
    }
}