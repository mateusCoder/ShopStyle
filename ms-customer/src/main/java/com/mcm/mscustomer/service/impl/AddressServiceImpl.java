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
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final ModelMapper mapper;

    private final AddressRepository addressRepository;

    private final CustomerRepository customerRepository;

    @Override
    public URI createAddressAndAssociateWithCustomer(AddressRequest addressRequest) {
        Address address = mapper.map(addressRequest, Address.class);
        Customer customer = customerRepository.findById(addressRequest.getCustomerId()).orElseThrow(NotFoundException::new);
        addressRepository.save(address);
        customer.setAddress(address);
        address.setCustomer(customer);
        addressRepository.save(address);
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(address.getId()).toUri();
    }

    @Override
    public AddressResponse updateAddress(Long id, AddressRequestUpdate addressRequest) {
        Address existingAddress = getAndValidateAddressById(id);
        Address updatedAddress = mapper.map(addressRequest, Address.class);
        updatedAddress.setId(id);
        updatedAddress.setCustomerId(existingAddress.getCustomerId());
        return mapper.map(addressRepository.save(updatedAddress), AddressResponse.class);
    }

    @Override
    public void deleteAddress(Long id) {
        getAndValidateAddressById(id);
        addressRepository.deleteById(id);
    }

    private Address getAndValidateAddressById(Long id){
        return addressRepository.findById(id).orElseThrow(NotFoundException::new);
    }
}
