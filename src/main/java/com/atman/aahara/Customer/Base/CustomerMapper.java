package com.atman.aahara.Customer.Base;


import com.atman.aahara.Customer.Credentials.Dto.CustomerCredentialRequest;
import com.atman.aahara.Customer.Credentials.Dto.CustomerCredentialResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {})
public interface CustomerMapper {
    CustomerCredentialResponse customerToCredential(Customer customer);
    Customer credentialsToCustomer(CustomerCredentialRequest customerCredentialRequest);
    Customer mapExistingFields(Customer customer);
}
