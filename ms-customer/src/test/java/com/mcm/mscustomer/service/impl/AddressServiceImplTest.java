package com.mcm.mscustomer.service.impl;

import com.mcm.mscustomer.factory.AddressScenarioFactory;
import com.mcm.mscustomer.factory.CustomerScenarioFactory;
import com.mcm.mscustomer.model.entities.Address;
import com.mcm.mscustomer.model.entities.Customer;
import com.mcm.mscustomer.repository.AddressRepository;
import com.mcm.mscustomer.repository.CustomerRepository;
import jakarta.ws.rs.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureTestDatabase
class AddressServiceImplTest {

    @InjectMocks
    private AddressServiceImpl addressService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AddressRepository addressRepository;

    @Spy
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setSkipNullEnabled(true);
    }

    @Test
    void createAddressAndAssociateWithCustomer_WhenSendValidAddressRequest_ExpectedSuccess() throws Exception {
        when(customerRepository.findById(anyString())).thenReturn(Optional.of(CustomerScenarioFactory.getSecondCustomer()));
        when(addressRepository.save(any(Address.class))).thenReturn(AddressScenarioFactory.getAddress());
        when(customerRepository.save(any(Customer.class))).thenReturn(CustomerScenarioFactory.getSecondCustomer());

        var response = addressService.createAddressAndAssociateWithCustomer(AddressScenarioFactory.getAddressRequest());

        assertNotNull(response);
        assertEquals(URI.class, response.getClass());
    }

    @Test
    void createAddressAndAssociateWithCustomer_WhenSendInvalidCustomerId_ExpectedBadRequestException() throws Exception {
        when(addressRepository.save(any(Address.class))).thenReturn(AddressScenarioFactory.getAddress());
        when(customerRepository.save(any(Customer.class))).thenReturn(CustomerScenarioFactory.getSecondCustomer());

        try {
            addressService.createAddressAndAssociateWithCustomer(AddressScenarioFactory.getAddressRequest());
        }catch (NotFoundException e){
            assertEquals(NotFoundException.class, e.getClass());
        }
    }

    @Test
    void updateAddress_WhenSendValidRequestForUpdateAddress_ExpectedSuccess() throws Exception {
        Long ID = 1L;
        when(addressRepository.findById(anyLong())).thenReturn(Optional.of(AddressScenarioFactory.getAddress()));
        when(addressRepository.save(any(Address.class))).thenReturn(AddressScenarioFactory.getAddress());

        var response = addressService.updateAddress(ID, AddressScenarioFactory.getAddressRequestUpdate());

        assertNotNull(response);
        assertEquals(1L, response.getId());
    }

    @Test
    void updateAddress_WhenSendInvalidIDToRequestForUpdateAddress_ExpectedNotFoundException() throws Exception {
        when(addressRepository.save(any(Address.class))).thenReturn(AddressScenarioFactory.getAddress());

        try {
            addressService.updateAddress(1L, AddressScenarioFactory.getAddressRequestUpdate());
        }catch (NotFoundException e){
            assertEquals(NotFoundException.class, e.getClass());
        }
    }

    @Test
    void deleteAddress_WhenSendValidAddressId_ExpectedSuccess() {
        final Long ID = AddressScenarioFactory.getSecondAddress().getId();
        when(addressRepository.findById(anyLong())).thenReturn(Optional.of(AddressScenarioFactory.getSecondAddress()));
        doNothing().when(addressRepository).deleteById(anyLong());

        addressService.deleteAddress(ID);

        verify(addressRepository, times(1)).deleteById(ID);
    }

    @Test
    void deleteAddress_WhenSendInvalidAddressId_ExpectedNotFoundException() {
        try {
            addressService.deleteAddress(1L);
        } catch (NotFoundException e){
            assertEquals(NotFoundException.class, e.getClass());
        }
    }
}