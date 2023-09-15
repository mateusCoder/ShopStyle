package com.mcm.mscustomer.controller;

import com.mcm.mscustomer.model.dto.request.CustomerRequest;
import com.mcm.mscustomer.model.dto.response.CustomerResponse;
import com.mcm.mscustomer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    private static final Logger LOGGER = LogManager.getLogger(CustomerController.class);

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest customerRequest){
        LOGGER.info("[(POST) - createCustomer | Body: {}]", customerRequest);
        return ResponseEntity.created(customerService.createCustomer(customerRequest)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable String id,
                                                           @Valid @RequestBody CustomerRequest customerRequest){
        LOGGER.info("[(PUT) - updateCustomer | ID: {} | Body: {}]", id, customerRequest);
        return ResponseEntity.ok(customerService.updateCustomer(id, customerRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findCustomerById(@PathVariable String id){
        LOGGER.info("[(GET) - findCustomerById | ID: {}", id);
        return ResponseEntity.ok(customerService.findById(id));
    }
}
