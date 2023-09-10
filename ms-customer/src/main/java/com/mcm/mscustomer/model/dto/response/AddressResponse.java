package com.mcm.mscustomer.model.dto.response;

import com.mcm.mscustomer.model.enums.State;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddressResponse {

    private Long id;

    private State state;

    private String city;

    private String district;

    private String street;

    private String number;

    private String cep;

    private String complement;

    private UUID customerId;

}
