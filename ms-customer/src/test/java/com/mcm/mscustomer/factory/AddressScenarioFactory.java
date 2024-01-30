package com.mcm.mscustomer.factory;

import com.mcm.mscustomer.model.dto.request.AddressRequest;
import com.mcm.mscustomer.model.dto.request.AddressRequestUpdate;
import com.mcm.mscustomer.model.entities.Address;
import com.mcm.mscustomer.model.enums.State;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public class AddressScenarioFactory {

    private static final Long ID = 1L;
    private static final State STATE = State.ALAGOAS;
    private static final String CITY = "Fortaleza";
    private static final String DISTRICT = "Conjunto Ceará";
    private static final String STREET = "Rua 202B";
    private static final String NUMBER = "902";
    private static final String CEP = "60530-280";
    private static final String COMPLEMENT = "";
    private static final String CUSTOMER_ID = "pv88dyesnj";

    private static final ModelMapper mapper =  new ModelMapper();

    public static Address getAddress(){
        return Address.builder()
                .id(ID)
                .state(STATE)
                .city(CITY)
                .district(DISTRICT)
                .street(STREET)
                .number(NUMBER)
                .cep(CEP)
                .complement(COMPLEMENT)
                .customerId(CUSTOMER_ID)
                .build();
    }

    public static Address getSecondAddress(){
        Address address = getAddress();
        address.setId(10L);
        address.setCustomerId("pv88dyesnj");
        return address;
    }

    public static AddressRequest getAddressRequest(){
        return AddressRequest.builder()
                .state(STATE)
                .city(CITY)
                .district(DISTRICT)
                .street(STREET)
                .number(NUMBER)
                .cep(CEP)
                .complement(COMPLEMENT)
                .customerId(CUSTOMER_ID)
                .build();
    }

    public static AddressRequestUpdate getAddressRequestUpdate(){
        return mapper.map(getAddressRequest(), AddressRequestUpdate.class);
    }

}
