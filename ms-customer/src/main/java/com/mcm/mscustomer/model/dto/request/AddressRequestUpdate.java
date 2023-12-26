package com.mcm.mscustomer.model.dto.request;

import com.mcm.mscustomer.model.enums.State;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddressRequestUpdate {

    private State state;

    private String city;

    private String district;

    private String street;

    private String number;

    private String cep;

    private String complement;

}
