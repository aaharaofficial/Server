package com.atman.aahara.Planner.Meal;

import com.atman.aahara.Exception.ResourceNotFoundException;
import com.atman.aahara.Recipe.Enum.MealType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlannerMealOutBound implements PlannerMealService {

    private final PlannerMealRepository plannerMealRepository;


    @Override
    public PlannerMeal savePlannerMeal(PlannerMeal plannerMeal) {
        return plannerMealRepository.save(plannerMeal);
    }


    @Override
    public PlannerMeal findById(UUID id) {
        return plannerMealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PlannerMeal not found with id " + id));
    }


    @Override
    public List<PlannerMeal> findAll() {
        return plannerMealRepository.findAll();
    }


    @Override
    public void deleteById(UUID id) {
        if (!plannerMealRepository.existsById(id)) {
            throw new ResourceNotFoundException("PlannerMeal not found with id " + id);
        }
        plannerMealRepository.deleteById(id);
    }

    @Override
    public void delete(PlannerMeal plannerMeal) {
        plannerMealRepository.delete(plannerMeal);
    }


    @Override
    public List<PlannerMeal> findByMealType(MealType mealType) {
        return plannerMealRepository.findByMealType(mealType);
    }


    @Override
    public List<PlannerMeal> findByMealTypeAndPlannerId(MealType mealType, UUID plannerId) {
        return plannerMealRepository.findByMealTypeAndPlanner_Id(mealType, plannerId);
    }



}
