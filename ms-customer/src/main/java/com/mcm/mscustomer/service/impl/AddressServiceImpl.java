package com.mcm.mscustomer.service.impl;

import com.mcm.mscustomer.model.dto.request.AddressRequest;
import com.mcm.mscustomer.model.dto.request.AddressRequestUpdate;
import com.mcm.mscustomer.model.dto.response.AddressResponse;
import com.mcm.mscustomer.model.entities.Address;
import com.mcm.mscustomer.model.entities.Customer;
import com.mcm.mscustomer.repository.AddressRepository;
import com.mcm.mscustomer.repository.CustomerRepository;
import com.mcm.mscustomer.service.AddressService;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final ModelMapper mapper;

    private final AddressRepository addressRepository;

    private final CustomerRepository customerRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressServiceImpl.class);

    @Override
    public URI createAddressAndAssociateWithCustomer(AddressRequest addressRequest) {
        LOGGER.info("[createAddressAndAssociateWithCustomer - Start create Address | Body={}]", addressRequest);
        Address address = mapper.map(addressRequest, Address.class);
        Customer customer = customerRepository.findById(addressRequest.getCustomerId()).orElseThrow(NotFoundException::new);
        addressRepository.save(address);
        customer.setAddress(address);
        LOGGER.info("[createAddressAndAssociateWithCustomer - ASSOCIATED CUSTOMER | Customer_Id={}]", customer.getId());
        address.setCustomer(customer);
        addressRepository.save(address);
        LOGGER.info("[createAddressAndAssociateWithCustomer - ADDRESS SAVED IN DATABASE | Address_Id={}]", address.getId());
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(address.getId()).toUri();
    }

    @Override
    public AddressResponse updateAddress(Long id, AddressRequestUpdate addressRequest) {
        LOGGER.info("[updateAddress - Start update Address | Body={}, Address_Id={}]", addressRequest, id);
        Address existingAddress = getAndValidateAddressById(id);
        Address updatedAddress = mapper.map(addressRequest, Address.class);
        updatedAddress.setId(id);
        updatedAddress.setCustomerId(existingAddress.getCustomerId());
        LOGGER.info("[updateAddress - ADDRESS UPDATED IN DATABASE]");
        return mapper.map(addressRepository.save(updatedAddress), AddressResponse.class);
    }

    @Override
    public void deleteAddress(Long id) {
        LOGGER.info("[deleteAddress - Start delete address | Address_Id={}]", id);
        getAndValidateAddressById(id);
        addressRepository.deleteById(id);
        LOGGER.info("[deleteAddress - ADDRESS DELETED]");
    }

    private Address getAndValidateAddressById(Long id){
        return addressRepository.findById(id).orElseThrow(NotFoundException::new);
    }
}
