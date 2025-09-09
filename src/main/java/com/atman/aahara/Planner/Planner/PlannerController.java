package com.atman.aahara.Planner.Planner;

import com.atman.aahara.Planner.Planner.Dto.PlannerGlobalResponseDto;
import com.atman.aahara.Planner.Planner.Dto.PlannerRequestDto;
import com.atman.aahara.Planner.Planner.Dto.PlannerResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/planner")
@RequiredArgsConstructor
@Slf4j
public class PlannerController {

    private final PlannerApplication plannerApplication;
    private final PlannerService plannerService;
    private final PlannerMapper plannerMapper;

    @Operation(summary = "Get planner by ID")
    @GetMapping("/{plannerId}")
    public ResponseEntity<PlannerGlobalResponseDto> getPlannerById(@PathVariable UUID plannerId) {
        Planner plannerForToday = plannerService.getPlannerById(plannerId);
        PlannerGlobalResponseDto plannerGlobalResponseDto = plannerMapper.entityToGlobalResponse(plannerForToday);
        return ResponseEntity.ok(plannerGlobalResponseDto);
    }

    @Operation(summary = "Get today's planner for a family")
    @GetMapping("/today/{familyId}")
    public ResponseEntity<PlannerGlobalResponseDto> getPlannerForToday(@PathVariable UUID familyId) {
        Planner plannerForToday = plannerService.getPlannerForToday(familyId);
        PlannerGlobalResponseDto plannerGlobalResponseDto = plannerMapper.entityToGlobalResponse(plannerForToday);
        return ResponseEntity.ok(plannerGlobalResponseDto);
    }

    @Operation(summary = "Create a new planner")
    @PostMapping
    public ResponseEntity<PlannerResponseDto> createPlanner(@RequestBody PlannerRequestDto plannerRequestDto) {
        Planner plannerForDate = plannerApplication.createPlannerForDate(plannerRequestDto.getFamilyId(), plannerRequestDto.getDate());
        PlannerResponseDto plannerResponseDto = plannerMapper.entityToResponse(plannerForDate);
        return ResponseEntity.status(201).body(plannerResponseDto);
    }
}
