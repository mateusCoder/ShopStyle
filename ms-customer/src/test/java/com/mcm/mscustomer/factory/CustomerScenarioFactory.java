package com.mcm.mscustomer.factory;

import com.mcm.mscustomer.model.dto.request.CustomerRequest;
import com.mcm.mscustomer.model.dto.response.CustomerResponse;
import com.mcm.mscustomer.model.entities.Customer;
import com.mcm.mscustomer.model.enums.Sex;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;

@RequiredArgsConstructor
public class CustomerScenarioFactory {

    private static final String ID = "pv88dyesnj";
    private static final String FIRST_NAME = "José";
    private static final String LAST_NAME = "Cardoso";
    private static final Sex SEX = Sex.MASCULINO;
    private static final String CPF = "566.229.850-57";
    private static final LocalDate BIRTHDATE = LocalDate.of(1999, 8, 1);
    private static final String EMAIL = "mateus@email.com";
    private static final String PASSWORD = "1234546";
    private static final boolean ACTIVE  = true;

    private static final ModelMapper mapper =  new ModelMapper();

    public static Customer getCustomer(){
        return Customer.builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .sex(SEX)
                .cpf(CPF)
                .birthdate(BIRTHDATE)
                .email(EMAIL)
                .password(PASSWORD)
                .active(ACTIVE)
                .addresses(new ArrayList<>())
                .build();
    }

    public static Customer getSecondCustomer(){
        Customer customer = getCustomer();
        customer.setEmail("ma@email.com");
        customer.setCpf("830.010.480-18");
        return customer;
    }

    public static Customer getThirdCustomer(){
        Customer customer = getCustomer();
        customer.setEmail("macardo@email.com");
        customer.setCpf("180.894.990-07");
        return customer;
    }

    public static Customer getFourthCustomer(){
        Customer customer = getCustomer();
        customer.setEmail("m@email.com");
        customer.setCpf("037.763.080-28");
        return customer;
    }

    public static CustomerRequest getCustomerRequest(){
        return mapper.map(getCustomer(), CustomerRequest.class);
    }

    public static CustomerRequest getCustomerRequestForUpdateEmail(){
        CustomerRequest customerRequest = mapper.map(getCustomer(), CustomerRequest.class);
        customerRequest.setEmail("mac_updated@email.com");
        return customerRequest;
    }

    public static CustomerResponse getCustomerResponse(){
        return mapper.map(getCustomer(), CustomerResponse.class);
    }

}
