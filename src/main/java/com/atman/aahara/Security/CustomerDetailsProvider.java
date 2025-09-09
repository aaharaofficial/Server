package com.atman.aahara.Security;


import com.atman.aahara.Customer.Domain.Customer;
import com.atman.aahara.Customer.Port.CustomerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("CUSTOMER")
@RequiredArgsConstructor
public class CustomerDetailsProvider implements UserDetailsProvider {

    private final CustomerUseCase customerUseCase; // inject the port

    @Override
    public Object loadUser(String mobileNumber) {
        // Use the port to fetch customer domain object
        Customer customer = customerUseCase.getCustomerByMobileNumber(mobileNumber);

        if (customer == null) {
            throw new RuntimeException("Customer not found with mobile: " + mobileNumber);
        }

        return customer;
    }
}
