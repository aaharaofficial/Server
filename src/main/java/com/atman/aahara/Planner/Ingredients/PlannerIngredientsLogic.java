package com.atman.aahara.Planner.Ingredients;

import com.atman.aahara.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlannerIngredientsLogic implements PlannerIngredientsService {

    private final PlannerIngredientsRepository plannerIngredientRepository;

    @Override
    public PlannerIngredients save(PlannerIngredients ingredient) {
        return plannerIngredientRepository.save(ingredient);
    }

    @Override
    public void delete(PlannerIngredients ingredient) {
        plannerIngredientRepository.delete(ingredient);
    }

    @Override
    public void deleteById(UUID id) {
        if (!plannerIngredientRepository.existsById(id)) {
            throw new ResourceNotFoundException("PlannerIngredients not found with id " + id);
        }
        plannerIngredientRepository.deleteById(id);
    }

    @Override
    public PlannerIngredients findById(UUID id) {
        return plannerIngredientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PlannerIngredients not found with id " + id));
    }

    @Override
    public List<PlannerIngredients> findAll() {
        return plannerIngredientRepository.findAll();
    }

    @Override
    public List<PlannerIngredients> findAllByMealId(UUID mealId) {
        return plannerIngredientRepository.findByMeal_Id(mealId);
    }

    @Override
    public List<PlannerIngredients> findAllByInventoryId(UUID inventoryId) {
        return plannerIngredientRepository.findByInventory_Id(inventoryId);
    }
}
