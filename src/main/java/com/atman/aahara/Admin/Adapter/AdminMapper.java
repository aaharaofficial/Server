package com.atman.aahara.Admin.Adapter;

import com.atman.aahara.Admin.Domain.Admin;
import com.atman.aahara.Admin.Infra.AdminEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface AdminMapper {

    Admin toDomain(AdminEntity entity);

    AdminEntity toEntity(Admin domain);

    void updateEntityFromDomain(Admin domain, @MappingTarget AdminEntity entity);
}
