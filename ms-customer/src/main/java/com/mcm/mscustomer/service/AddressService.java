package com.mcm.mscustomer.service;

import com.mcm.mscustomer.model.dto.request.AddressRequest;
import com.mcm.mscustomer.model.dto.request.AddressRequestUpdate;
import com.mcm.mscustomer.model.dto.response.AddressResponse;

import java.net.URI;

public interface AddressService {

    URI createAddressAndAssociateWithCustomer(AddressRequest addressRequest);

    AddressResponse updateAddress(Long id, AddressRequestUpdate addressRequest);

    void deleteAddress(Long id);
}
