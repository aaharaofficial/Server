package com.atman.aahara.OneTimePass.Adapters;

import com.atman.aahara.OneTimePass.Domain.OTPSession;
import com.atman.aahara.OneTimePass.infrastructure.OTPEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OTPMapper {

    OTPEntity toEntity(OTPSession domain);

    OTPSession toDomain(OTPEntity entity);
}
