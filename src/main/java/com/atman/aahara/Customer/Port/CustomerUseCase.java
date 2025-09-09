package com.atman.aahara.Customer.Port;

import com.atman.aahara.Customer.Domain.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerUseCase {
    Customer saveCustomer(Customer customer);

    Customer updateCustomer(UUID id , Customer customer);

    Customer getCustomer(UUID customerId);

    Customer getCustomerByMobileNumber(String mobileNumber);

    List<Customer> getCustomersByFamilyID(UUID familyID);

    List<Customer> getAllCustomers();

    void deleteCustomer(UUID id);

    void deleteCustomer(Customer customer);
}
