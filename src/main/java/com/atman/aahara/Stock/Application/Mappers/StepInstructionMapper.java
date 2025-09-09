package com.atman.aahara.Stock.Application.Mappers;

import com.atman.aahara.Stock.Domain.Model.StepInstruction;
import com.atman.aahara.Stock.Infra.Persistance.StepInstructionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StepInstructionMapper {
    StepInstruction toDomain(StepInstructionEntity StepInstructionEntity);
    StepInstructionEntity toEntity(StepInstruction StepInstruction);
}
