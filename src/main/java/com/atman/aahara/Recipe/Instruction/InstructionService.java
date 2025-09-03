package com.atman.aahara.Recipe.Instruction;

import java.util.List;
import java.util.UUID;

public interface InstructionService {

    Instruction saveInstruction(Instruction instruction);

    List<Instruction> saveListOfInstruction(List<Instruction> instructions);

    void incrementSteps(UUID recipeId, Integer step);

    Instruction getInstruction(UUID recipeId, Integer step);

    void decrementSteps(UUID recipeId, Integer step);

    void deleteInstruction(UUID id);

    void deleteInstruction(Instruction instruction);

    List<Instruction> getAllInstruction();

    List<Instruction> getAllInstructionForRecipe(UUID recipe);

    Instruction getInstruction(UUID id);

    void swapInstruction(UUID instructionId, Integer newStep);
}
