package com.mcm.mscustomer.service.impl;

import com.mcm.mscustomer.model.dto.request.CustomerRequest;
import com.mcm.mscustomer.model.dto.response.CustomerResponse;
import com.mcm.mscustomer.model.entities.Customer;
import com.mcm.mscustomer.repository.CustomerRepository;
import com.mcm.mscustomer.service.CustomerService;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final ModelMapper mapper;

    private final CustomerRepository customerRepository;

    private static final Logger LOGGER = LogManager.getLogger(CustomerServiceImpl.class);

    @Override
    public URI createCustomer(CustomerRequest customerRequest) {
        Customer customer = mapper.map(customerRequest, Customer.class);
        customerRepository.save(customer);
        LOGGER.info("[createCustomer - CUSTOMER SAVED IN DATABASE | ID:{} | Object:{}]", customer.getId(), customer);
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(customer.getId()).toUri();
    }

    @Override
    public CustomerResponse updateCustomer(String id, CustomerRequest customerRequest) {
        var customer = customerRepository.findById(id).orElseThrow(NotFoundException::new);
        customer = mapper.map(customerRequest, Customer.class);
        customer.setId(id);
        customerRepository.save(customer);
        LOGGER.info("[updateCustomer - CUSTOMER UPDATED IN DATABASE ID:{} | Object:{}]", customer.getId(), customer);
        return mapper.map(customer, CustomerResponse.class);
    }

    @Override
    public CustomerResponse findById(String id) {
        var customer = customerRepository.findById(id).orElseThrow(NotFoundException::new);
        LOGGER.info("[findById - CUSTOMER FOUD | ID:{} | Object:{}]", customer.getId(), customer);
        return mapper.map(customer, CustomerResponse.class);
    }
}
