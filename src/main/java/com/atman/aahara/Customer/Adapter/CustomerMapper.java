package com.atman.aahara.Customer.Adapter;

import com.atman.aahara.Customer.Domain.Customer;
import com.atman.aahara.Customer.Infra.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerEntity toEntity(Customer domain);

    Customer toDomain(CustomerEntity entity);

    void updateEntityFromDomain(Customer domain, CustomerEntity entity);
}
