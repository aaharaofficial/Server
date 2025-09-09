package com.atman.aahara.Customer.Application;

import com.atman.aahara.Customer.Domain.Customer;
import com.atman.aahara.Customer.Adapter.CustomerMapper;
import com.atman.aahara.Customer.Port.CustomerStoragePort;
import com.atman.aahara.Customer.Port.CustomerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerApplicationService implements CustomerUseCase {

    private final CustomerStoragePort storagePort;
    private final CustomerMapper mapper;

    @Override
    public Customer saveCustomer(Customer customer) {
        return storagePort.saveCustomer(customer);
    }

    @Override
    public Customer updateCustomer(UUID id, Customer customer) {
        Customer existing = storagePort.getCustomer(id);
        if (existing == null) throw new RuntimeException("Customer not found with id: " + id);

        mapper.updateEntityFromDomain(customer, mapper.toEntity(existing));
        return storagePort.saveCustomer(existing);
    }

    @Override
    public Customer getCustomer(UUID customerId) {
        return storagePort.getCustomer(customerId);
    }

    @Override
    public Customer getCustomerByMobileNumber(String mobileNumber) {
        return storagePort.getCustomerByMobileNumber(mobileNumber);
    }

    @Override
    public List<Customer> getCustomersByFamilyID(UUID familyID) {
        return storagePort.getCustomersByFamilyID(familyID);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return storagePort.getAllCustomers();
    }

    @Override
    public void deleteCustomer(UUID id) {
        storagePort.deleteCustomer(id);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        storagePort.deleteCustomer(customer);
    }
}
