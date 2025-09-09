package com.atman.aahara.Customer.Port;

import com.atman.aahara.Customer.Domain.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerStoragePort {

    Customer saveCustomer(Customer customer);

    Customer getCustomer(UUID customerId);

    Customer getCustomerByMobileNumber(String mobileNumber);

    List<Customer> getCustomersByFamilyID(UUID familyID);

    List<Customer> getAllCustomers();

    boolean deleteCustomer(UUID id);

    boolean deleteCustomer(Customer customer);
}
