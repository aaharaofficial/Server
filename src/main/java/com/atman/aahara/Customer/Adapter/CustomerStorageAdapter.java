package com.atman.aahara.Customer.Adapter;

import com.atman.aahara.Customer.Domain.Customer;
import com.atman.aahara.Customer.Infra.CustomerEntity;
import com.atman.aahara.Customer.Infra.CustomerRepository;
import com.atman.aahara.Customer.Port.CustomerStoragePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomerStorageAdapter implements CustomerStoragePort {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    @Override
    public Customer saveCustomer(Customer customer) {
        CustomerEntity entity = mapper.toEntity(customer);
        CustomerEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Customer getCustomer(UUID customerId) {
        return repository.findById(customerId)
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    public Customer getCustomerByMobileNumber(String mobileNumber) {
        return repository.findByMobileNumber(mobileNumber)
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    public List<Customer> getCustomersByFamilyID(UUID familyID) {
        return repository.findAllByFamily_id(familyID)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> getAllCustomers() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteCustomer(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCustomer(Customer customer) {
        if (repository.existsById(customer.getId())) {
            repository.deleteById(customer.getId());
            return true;
        }
        return false;
    }
}
