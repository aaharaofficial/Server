package com.atman.aahara.Planner.Planner;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface PlannerService {
    List<Planner> saveAllPlanners(List<Planner> planners);

    Planner savePlanner(Planner planner);

    void deletePlanner(UUID id);

    Planner getPlannerById(UUID plannerId);

    Planner getPlannerForToday(UUID familyId);

    Planner getPlannerByDateAndFamily(UUID familyId, LocalDate date);

    List<Planner> getAllPlannerByDate(LocalDate date);

    List<Planner> getPlannerForWeek(UUID familyId);
}
