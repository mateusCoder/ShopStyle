package com.mcm.mscustomer.controller;

import com.mcm.mscustomer.model.dto.request.AddressRequest;
import com.mcm.mscustomer.model.dto.request.AddressRequestUpdate;
import com.mcm.mscustomer.model.dto.request.CustomerRequest;
import com.mcm.mscustomer.model.dto.response.AddressResponse;
import com.mcm.mscustomer.model.dto.response.CustomerResponse;
import com.mcm.mscustomer.service.AddressService;
import com.mcm.mscustomer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    private final AddressService addressService;

    private static final Logger LOGGER = LogManager.getLogger(CustomerController.class);

    @PostMapping("/customers")
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest customerRequest){
        LOGGER.info("[(POST) - createCustomer | Body: {}]", customerRequest);
        return ResponseEntity.created(customerService.createCustomer(customerRequest)).build();
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable String id,
                                                           @Valid @RequestBody CustomerRequest customerRequest){
        LOGGER.info("[(PUT) - updateCustomer | ID: {} | Body: {}]", id, customerRequest);
        return ResponseEntity.ok(customerService.updateCustomer(id, customerRequest));
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerResponse> findCustomerById(@PathVariable String id){
        LOGGER.info("[(GET) - findCustomerById | ID: {}", id);
        return ResponseEntity.ok(customerService.findById(id));
    }

    @PostMapping("/addresses")
    public  ResponseEntity<AddressResponse> createAddress(@Valid @RequestBody AddressRequest addressRequest){
        return ResponseEntity.created(addressService.createAddress(addressRequest)).build();
    }

    @PutMapping("/addresses/{id}")
    public ResponseEntity<AddressResponse> updateAddress(@PathVariable Long id,
                                                         @Valid @RequestBody AddressRequestUpdate addressRequest){
        return ResponseEntity.ok(addressService.updateAddress(id, addressRequest));
    }

    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long id){
        addressService.deleteAddress(id);
        return ResponseEntity.ok().build();
    }
}
