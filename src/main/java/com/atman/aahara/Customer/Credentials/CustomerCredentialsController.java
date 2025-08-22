package com.atman.aahara.Customer.Credentials;

import com.atman.aahara.Customer.Base.Customer;
import com.atman.aahara.Customer.Base.CustomerMapper;
import com.atman.aahara.Customer.Base.CustomerService;
import com.atman.aahara.Customer.Credentials.Dto.CustomerCredentialRequest;
import com.atman.aahara.Customer.Credentials.Dto.CustomerCredentialResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
@Slf4j
@Tag(name = "Customer", description = "Customer related APIs")
public class CustomerCredentialsController implements CustomerCredentialsApi {

    private final CustomerMapper customerMapper;
    private final CustomerService customerService;

    @GetMapping("/{id}")
    @Operation(summary = "get Customer Credentials using Customer Id ", description = "get Customer Credentials using Customer Id")
    @Override
    public ResponseEntity<CustomerCredentialResponse>  getCredentialsById(@Valid @PathVariable("id") UUID customerId){
        Customer customerByID = customerService.getCustomer(customerId);
        CustomerCredentialResponse customerCredentialResponse = customerMapper.customerToCredential(customerByID);
        return ResponseEntity.ok(customerCredentialResponse);
    }

    @PostMapping()
    @Operation(summary = " save Customer Credentials  ", description = " save Customer Credentials ")
    @Override
    public ResponseEntity<CustomerCredentialResponse>  saveCredentials(@Valid @RequestBody CustomerCredentialRequest customerCredentialRequest){
        Customer customer = customerMapper.credentialsToCustomer(customerCredentialRequest);
        Customer savedCustomer = customerService.saveCustomer(customer);
        CustomerCredentialResponse customerCredentialResponse = customerMapper.customerToCredential(savedCustomer);
        return ResponseEntity.ok(customerCredentialResponse);
    }


    @PutMapping("/{id}")
    @Operation(summary = " update Customer Credentials  ", description = " update Customer Credentials Using Customer Id ")
    @Override
    public ResponseEntity<CustomerCredentialResponse> updateCredentialsForCustomerUsingId(@Valid @PathVariable("id") UUID customerId, @Valid @RequestBody CustomerCredentialRequest customerCredentialRequest){
        Customer customerByID = customerService.getCustomer(customerId);
        Customer updatedCustomer = customerMapper.mapExistingFields(customerByID);
        Customer savedCustomer = customerService.saveCustomer(updatedCustomer);
        CustomerCredentialResponse customerCredentialResponse = customerMapper.customerToCredential(savedCustomer);
        return ResponseEntity.ok(customerCredentialResponse);
    }

}
