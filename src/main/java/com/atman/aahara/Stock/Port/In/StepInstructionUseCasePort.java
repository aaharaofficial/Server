package com.atman.aahara.Stock.Port.In;

import com.atman.aahara.Stock.Domain.Model.StepInstruction;

import java.util.List;
import java.util.UUID;

public interface StepInstructionUseCasePort {
    StepInstruction addInstruction(StepInstruction stepInstruction);
    void removeStepInstruction(UUID stepInstructionID);
    StepInstruction udpateStepInstruction(UUID stepInstructionID , StepInstruction stepInstruction);
    StepInstruction getStepInstruction(UUID stepInstructionID);
    List<StepInstruction> getAllStepInstructions();
}
