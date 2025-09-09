package com.atman.aahara.Stock.Application.Service;

import com.atman.aahara.Stock.Domain.Model.StepInstruction;
import com.atman.aahara.Stock.Port.In.StepInstructionUseCasePort;
import com.atman.aahara.Stock.Port.Out.StepInstructionRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StepInstructionApplicationService implements StepInstructionUseCasePort {

    private final StepInstructionRepositoryPort stepInstructionRepositoryPort;

    @Override
    public StepInstruction addInstruction(StepInstruction stepInstruction) {
        return stepInstructionRepositoryPort.save(stepInstruction);
    }

    @Override
    public void removeStepInstruction(UUID stepInstructionID) {
        stepInstructionRepositoryPort.deleteById(stepInstructionID);
    }

    @Override
    public StepInstruction udpateStepInstruction(UUID stepInstructionID, StepInstruction stepInstruction) {
        return null;
    }

    @Override
    public StepInstruction getStepInstruction(UUID stepInstructionID) {
        return stepInstructionRepositoryPort.findById(stepInstructionID)
                .orElseThrow(() -> new IllegalArgumentException(
                        "StepInstruction with ID " + stepInstructionID + " not found"));
    }

    @Override
    public List<StepInstruction> getAllStepInstructions() {
        return stepInstructionRepositoryPort.findAll();
    }
}
