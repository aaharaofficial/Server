package com.atman.aahara.Planner.Items;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PlannerItemRepository extends JpaRepository<PlannerItem, UUID> {

    List<PlannerItem> findAllByPlanner_Id(UUID plannerId);

    List<PlannerItem> findAllByInventory_Id(UUID inventoryId);
}
