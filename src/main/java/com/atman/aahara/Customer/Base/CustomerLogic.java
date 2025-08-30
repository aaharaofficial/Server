package com.atman.aahara.Customer.Base;

import com.atman.aahara.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@RequiredArgsConstructor
@Service
public class CustomerLogic implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public Customer getCustomer(UUID customerID) {
        return customerRepository.findById(customerID)
                .orElseThrow(() -> new ResourceNotFoundException("Customer Not found " + customerID));
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public boolean doesCustomerExists(String mobileNumber) {
        return customerRepository.existsByMobileNumber(mobileNumber);
    }

    @Override
    public Customer getCustomerByMobileNumber(String mobileNumber) {
        return customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer Not found " + mobileNumber));
    }

    @Override
    public void deleteCustomer(UUID customerID) {
        if (customerRepository.existsById(customerID)) {
            customerRepository.deleteById(customerID);
        }
    }
}
