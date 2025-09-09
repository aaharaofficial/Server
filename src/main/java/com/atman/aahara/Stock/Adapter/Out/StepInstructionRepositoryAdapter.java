package com.atman.aahara.Stock.Adapter.Out;

import com.atman.aahara.Stock.Application.Mappers.StepInstructionMapper;
import com.atman.aahara.Stock.Domain.Model.StepInstruction;
import com.atman.aahara.Stock.Infra.Persistance.StepInstructionEntity;
import com.atman.aahara.Stock.Infra.Repository.StepInstructionJpaRepository;
import com.atman.aahara.Stock.Port.Out.StepInstructionRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StepInstructionRepositoryAdapter implements StepInstructionRepositoryPort {

    private final StepInstructionJpaRepository stepInstructionJpaRepository;
    private final StepInstructionMapper stepInstructionMapper;

    @Override
    public StepInstruction save(StepInstruction stepInstruction) {
        StepInstructionEntity entity = stepInstructionMapper.toEntity(stepInstruction);
        StepInstructionEntity savedEntity = stepInstructionJpaRepository.save(entity);
        return stepInstructionMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<StepInstruction> findById(UUID id) {
        return stepInstructionJpaRepository.findById(id)
                .map(stepInstructionMapper::toDomain);
    }

    @Override
    public List<StepInstruction> findAll() {
        return stepInstructionJpaRepository.findAll()
                .stream()
                .map(stepInstructionMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        stepInstructionJpaRepository.deleteById(id);
    }
}
