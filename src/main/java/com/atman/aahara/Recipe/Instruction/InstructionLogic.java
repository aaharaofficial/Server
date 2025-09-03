package com.atman.aahara.Recipe.Instruction;

import com.atman.aahara.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class InstructionLogic implements InstructionService {

    private final InstructionRepository instructionRepository;

    @Override
    public Instruction saveInstruction(Instruction instruction) {
        return instructionRepository.save(instruction);
    }

    @Override
    public List<Instruction> saveListOfInstruction(List<Instruction> instructions) {
        return instructionRepository.saveAll(instructions);
    }

    @Override
    public void incrementSteps(UUID recipeId, Integer step) {
        instructionRepository.incrementSteps(recipeId, step);
    }

    @Override
    public void decrementSteps(UUID recipeId, Integer step) {
        instructionRepository.decrementSteps(recipeId, step);
    }

    @Override
    public Instruction getInstruction(UUID recipeId, Integer step) {
        return instructionRepository.findByRecipeIdAndStep(recipeId, step)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Instruction not found for step " + step + " in recipe " + recipeId));
    }

    @Override
    public List<Instruction> getAllInstruction() {
        return instructionRepository.findAll();
    }

    @Override
    public List<Instruction> getAllInstructionForRecipe(UUID recipe) {
        return instructionRepository.findAllByRecipeId(recipe);
    }

    @Override
    public Instruction getInstruction(UUID id) {
        return instructionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instruction not found " + id));
    }

    @Override
    public void deleteInstruction(UUID id) {
        if (instructionRepository.existsById(id)) {
            instructionRepository.deleteById(id);
        }
    }

    @Override
    public void deleteInstruction(Instruction instruction) {
        instructionRepository.delete(instruction);
    }

    @Override
    public void swapInstruction(UUID instructionId, Integer newStep) {
        Instruction instructionA = getInstruction(instructionId);
        Instruction instructionB = getInstruction(instructionA.getRecipe().getId(), newStep);

        int oldStep = instructionA.getStep();
        instructionA.setStep(newStep);
        instructionB.setStep(oldStep);

        saveListOfInstruction(List.of(instructionA, instructionB));
    }
}
