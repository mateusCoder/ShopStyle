package com.mcm.mscustomer.service.impl;

import com.mcm.mscustomer.factory.CustomerScenarioFactory;
import com.mcm.mscustomer.model.dto.request.CustomerRequest;
import com.mcm.mscustomer.model.dto.response.CustomerResponse;
import com.mcm.mscustomer.model.entities.Customer;
import com.mcm.mscustomer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class CustomerServiceImplTest {

    private static final String ID = "pv88dyesnj";

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Spy
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        Customer customer = CustomerScenarioFactory.getCustomer();
        CustomerRequest customerRequest = CustomerScenarioFactory.getCustomerRequest();
        CustomerResponse customerResponse = CustomerScenarioFactory.getCustomerResponse();
    }

    @Test
    void createCustomer_WhenSendValidCustomerRequest_ExpectedSuccess() {
        when(customerRepository.save(any(Customer.class))).thenReturn(CustomerScenarioFactory.getCustomer());

        var response = customerService.createCustomer(CustomerScenarioFactory.getCustomerRequest());

        assertNotNull(response);
        assertEquals(URI.class, response.getClass());
    }

    @Test
    void updateCustomer_WhenSendValidIdAndCustomerRequest_ExpectedSuccess() {
        when(customerRepository.findById(anyString())).thenReturn(Optional.of(CustomerScenarioFactory.getCustomer()));
        when(customerRepository.save(any(Customer.class))).thenReturn(CustomerScenarioFactory.getCustomer());

        var response = customerService.updateCustomer(ID, CustomerScenarioFactory.getCustomerRequest());

        assertNotNull(response);
        assertEquals(ID, response.getId());
    }

    @Test
    void findById_WhenSendValidId_ExpectedSuccess() {
        when(customerRepository.findById(anyString())).thenReturn(Optional.ofNullable(CustomerScenarioFactory.getCustomer()));

        var response = customerService.findById(ID);

        assertNotNull(response);
        assertEquals(ID, response.getId());
    }
}