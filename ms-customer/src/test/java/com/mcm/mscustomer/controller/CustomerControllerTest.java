package com.mcm.mscustomer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcm.mscustomer.factory.CustomerScenarioFactory;
import com.mcm.mscustomer.model.dto.response.CustomerResponse;
import com.mcm.mscustomer.model.entities.Customer;
import com.mcm.mscustomer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class CustomerControllerTest {

    private final MockMvc mockMvc;

    private final CustomerRepository customerRepository;

    private final ObjectMapper objectMapper;

    @Test
    void createCustomer_WhenSendValidCustomerRequest_ExpectedSuccess() throws Exception {
        String customerRequest = objectMapper.writeValueAsString(CustomerScenarioFactory.getCustomerRequest());

        mockMvc.perform(MockMvcRequestBuilders.post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerRequest))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void updateCustomer_WhenSendValidRequestForUpdateEmail_ExpectedSuccess() throws Exception {
        String customerRequest = objectMapper.writeValueAsString(CustomerScenarioFactory.getCustomerRequestForUpdateEmail());
        Customer customer = customerRepository.save(CustomerScenarioFactory.getSecondCustomer());

        mockMvc.perform(MockMvcRequestBuilders.put("/customers/{id}", customer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        var customerResponse = customerRepository.findById(customer.getId()).orElseThrow(null);
        Assertions.assertEquals(customerResponse.getEmail(), CustomerScenarioFactory.getCustomerRequestForUpdateEmail().getEmail());
    }

    @Test
    void findCustomerById_WhenSendValidId_ExpectedSuccess() throws Exception {
        Customer customer = customerRepository.save(CustomerScenarioFactory.getThirdCustomer());
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/customers/{id}", customer.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        CustomerResponse returnedCustomer = objectMapper.readValue(responseBody, CustomerResponse.class);
    }
}