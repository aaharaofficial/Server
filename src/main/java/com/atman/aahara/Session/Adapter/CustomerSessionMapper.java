package com.atman.aahara.Session.Adapter;


import com.atman.aahara.Session.Domain.CustomerSession;
import com.atman.aahara.Session.Infra.CustomerSessionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerSessionMapper {

    CustomerSession toDomain(CustomerSessionEntity entity);

    CustomerSessionEntity toEntity(CustomerSession domain);

}
