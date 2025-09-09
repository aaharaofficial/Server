package com.atman.aahara.Planner.Items;

import java.util.List;
import java.util.UUID;

public interface PlannerItemService {


    PlannerItem save(PlannerItem plannerItem);


    void delete(PlannerItem plannerItem);


    void deleteById(UUID id);


    PlannerItem findById(UUID id);


    List<PlannerItem> findAll();


    List<PlannerItem> findAllByPlannerId(UUID plannerId);


    List<PlannerItem> findAllByInventoryId(UUID inventoryId);
}
