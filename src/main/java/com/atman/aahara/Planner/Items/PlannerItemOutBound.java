package com.atman.aahara.Planner.Items;

import com.atman.aahara.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlannerItemOutBound implements PlannerItemService {

    private final PlannerItemRepository plannerItemRepository;

    @Override
    public PlannerItem save(PlannerItem plannerItem) {
        return plannerItemRepository.save(plannerItem);
    }

    @Override
    public void delete(PlannerItem plannerItem) {
        plannerItemRepository.delete(plannerItem);
    }

    @Override
    public void deleteById(UUID id) {
        if (!plannerItemRepository.existsById(id)) {
            throw new ResourceNotFoundException("PlannerItem not found with id " + id);
        }
        plannerItemRepository.deleteById(id);
    }

    @Override
    public PlannerItem findById(UUID id) {
        return plannerItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PlannerItem not found with id " + id));
    }

    @Override
    public List<PlannerItem> findAll() {
        return plannerItemRepository.findAll();
    }

    @Override
    public List<PlannerItem> findAllByPlannerId(UUID plannerId) {
        return plannerItemRepository.findAllByPlanner_Id(plannerId);
    }

    @Override
    public List<PlannerItem> findAllByInventoryId(UUID inventoryId) {
        return plannerItemRepository.findAllByInventory_Id(inventoryId);
    }
}
