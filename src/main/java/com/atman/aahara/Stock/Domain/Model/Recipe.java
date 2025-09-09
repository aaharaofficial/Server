package com.atman.aahara.Stock.Domain.Model;

import com.atman.aahara.Stock.Domain.Value.Money;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Recipe {

    private UUID id;
    private String name;
    private String description;
    private final List<StepInstruction> stepInstructions = new ArrayList<>();
    private Money recipeProcessingFee = Money.ZERO;

    public Money getTotalPrice() {
        Money stepsTotal = stepInstructions.stream()
                .map(StepInstruction::calculateStepPrice)
                .reduce(Money.ZERO, Money::add);

        return stepsTotal.add(recipeProcessingFee);
    }

    public void addStep(StepInstruction step) {
        stepInstructions.add(step.getStep() - 1, step);
        reorderSteps();
    }

    public void removeStep(int stepNumber) {
        stepInstructions.removeIf(s -> s.getStep() == stepNumber);
        reorderSteps();
    }

    private void reorderSteps() {
        for (int i = 0; i < stepInstructions.size(); i++) {
            stepInstructions.get(i).setStep(i + 1);
        }
    }

}
