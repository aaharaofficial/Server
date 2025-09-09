package com.atman.aahara.Planner.Items;

import com.atman.aahara.Planner.Planner.Planner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlannerItemApplication {

    private final PlannerItemOutBound plannerItemOutBound;

  public PlannerItem createPlannerItem(PlannerItem plannerItem){
      return plannerItemOutBound.save(plannerItem);
  }

  public void deletePlannerItem(UUID plannerItemId){
      plannerItemOutBound.deleteById(plannerItemId);
  }

  public PlannerItem swapPlannerItem(Planner sourcePlanner,Planner targetPlanner , PlannerItem plannerItem){
      return null;
  }

}
