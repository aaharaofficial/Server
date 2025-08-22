package com.atman.aahara.Customer.Credentials;

import com.atman.aahara.Customer.Credentials.Dto.CustomerCredentialRequest;
import com.atman.aahara.Customer.Credentials.Dto.CustomerCredentialResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface CustomerCredentialsApi {
    ResponseEntity<CustomerCredentialResponse> getCredentialsById(@Valid @PathVariable UUID customerId);

    ResponseEntity<CustomerCredentialResponse> saveCredentials(@Valid @RequestBody CustomerCredentialRequest customerCredentialRequest);

    ResponseEntity<CustomerCredentialResponse> updateCredentialsForCustomerUsingId(@Valid @PathVariable UUID customerId, @Valid @RequestBody CustomerCredentialRequest customerCredentialRequest);
}
