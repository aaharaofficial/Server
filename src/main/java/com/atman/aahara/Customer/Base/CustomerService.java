package com.atman.aahara.Customer.Base;

import java.util.UUID;

public interface CustomerService {
    Customer getCustomer(UUID customerID);

    Customer saveCustomer(Customer customer);

    void deleteCustomer(UUID customerID);

    Customer getCustomerByMobileNumber(String mobileNumber);

    boolean doesCustomerExists(String mobileNumber);
}
