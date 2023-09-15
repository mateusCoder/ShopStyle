package com.mcm.mscustomer.service;

import com.mcm.mscustomer.model.dto.request.CustomerRequest;
import com.mcm.mscustomer.model.dto.response.CustomerResponse;

import java.net.URI;

public interface CustomerService {

    URI createCustomer(CustomerRequest customerRequest);

    CustomerResponse updateCustomer(String id, CustomerRequest customerRequest);

    CustomerResponse findById(String id);
}
