package com.atman.aahara.Planner.Items;

import com.atman.aahara.Planner.Items.Dto.PlannerItemRequestDto;
import com.atman.aahara.Planner.Items.Dto.PlannerItemResponseDto;
import com.atman.aahara.Planner.Items.Dto.PlannerItemSwapRequest;
import com.atman.aahara.Planner.Items.Dto.PlannerItemUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PlannerItemController {

    private final PlannerItemInBound plannerItemInBound;

    public ResponseEntity<PlannerItemResponseDto> addPlannerItemToPlanner(PlannerItemRequestDto plannerItemRequestDto) {
        PlannerItemResponseDto plannerItemResponseDto = plannerItemInBound.addItemToPlanner(plannerItemRequestDto);
        return ResponseEntity.status(201).body(plannerItemResponseDto);
    }

    public void removePlannerItemFromPlanner(UUID plannerItemId) {
        plannerItemInBound.removeItemFromPlanner(plannerItemId);
    }

    public ResponseEntity<PlannerItemResponseDto> swapPlannerItem(PlannerItemSwapRequest plannerItemSwapRequest) {
        PlannerItemResponseDto plannerItemResponseDto = plannerItemInBound.swapPlannerItem(plannerItemSwapRequest);
        return ResponseEntity.status(201).body(plannerItemResponseDto);
    }

    public ResponseEntity<PlannerItemResponseDto> updatePlannerItem(PlannerItemUpdateRequest plannerItemUpdateRequest) {
        PlannerItemResponseDto plannerItemResponseDto = plannerItemInBound.updatePlannerItem(plannerItemUpdateRequest);
        return ResponseEntity.status(201).body(plannerItemResponseDto);
    }
}
