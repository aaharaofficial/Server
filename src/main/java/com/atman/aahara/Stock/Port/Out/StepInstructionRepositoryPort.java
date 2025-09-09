package com.atman.aahara.Stock.Port.Out;

import com.atman.aahara.Stock.Domain.Model.StepInstruction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StepInstructionRepositoryPort {
    StepInstruction save(StepInstruction StepInstruction);
    Optional<StepInstruction> findById(UUID id);
    List<StepInstruction> findAll();
    void deleteById(UUID id);
}
