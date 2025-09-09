package com.atman.aahara.Planner.Planner;

import com.atman.aahara.Planner.Planner.Dto.PlannerGlobalResponseDto;
import com.atman.aahara.Planner.Planner.Dto.PlannerRequestDto;
import com.atman.aahara.Planner.Planner.Dto.PlannerResponseDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlannerMapper {


    @Named("entityToGlobalResponse")
    @Mapping(target = "familyId", source = "family.id")
    PlannerGlobalResponseDto entityToGlobalResponse(Planner planner);

    @IterableMapping(qualifiedByName = "entityToGlobalResponse")
    List<PlannerGlobalResponseDto> entityToGlobalResponse(List<Planner> planner);


    PlannerResponseDto entityToResponse(Planner planner);

    @Mapping(target = "family", ignore = true)
    Planner requestToEntity(PlannerRequestDto plannerRequestDto);
}
