package com.atman.aahara.Planner.Items;

import com.atman.aahara.Planner.Items.Dto.PlannerItemRequestDto;
import com.atman.aahara.Planner.Items.Dto.PlannerItemResponseDto;
import com.atman.aahara.Planner.Items.Dto.PlannerItemSwapRequest;
import com.atman.aahara.Planner.Items.Dto.PlannerItemUpdateRequest;
import com.atman.aahara.Planner.Planner.Planner;
import com.atman.aahara.Planner.Planner.PlannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class PlannerItemInBound {


    private final InventoryService inventoryService;
    private final PlannerItemMapper plannerItemMapper;
    private final PlannerService plannerService;
    private final PlannerItemApplication plannerItemApplication;
    private final PlannerItemService plannerItemService;

    public PlannerItemResponseDto addItemToPlanner(PlannerItemRequestDto plannerItemRequestDto) {
        Planner plannerByDateAndFamily = plannerService.getPlannerByDateAndFamily(plannerItemRequestDto.getFamilyId(), plannerItemRequestDto.getPlannerDate());
        Inventory inventory = inventoryService.getInventory(plannerItemRequestDto.getInventoryId());
        PlannerItem plannerItem = plannerItemMapper.combineRequestAsEntity(inventory, plannerItemRequestDto);
        PlannerItem saved = plannerItemApplication.createPlannerItem(plannerItem);
        return plannerItemMapper.entityToResponse(saved);
    }

    public void removeItemFromPlanner(UUID plannerItemId) {
        plannerItemApplication.deletePlannerItem(plannerItemId);
    }

    public PlannerItemResponseDto swapPlannerItem(PlannerItemSwapRequest plannerItemSwapRequest) {
        PlannerItem plannerItemById = plannerItemService.findById(plannerItemSwapRequest.getId());
        Planner planner = plannerItemById.getPlanner();
        Planner plannerByDateAndFamily = plannerService.getPlannerByDateAndFamily(plannerItemSwapRequest.getFamilyId(), plannerItemSwapRequest.getToDate());
        PlannerItem plannerItem = plannerItemApplication.swapPlannerItem(planner,plannerByDateAndFamily,plannerItemById);
        return plannerItemMapper.entityToResponse(plannerItem);
    }

    public PlannerItemResponseDto updatePlannerItem(PlannerItemUpdateRequest plannerItemUpdateRequest){
        return null;
    }
}
