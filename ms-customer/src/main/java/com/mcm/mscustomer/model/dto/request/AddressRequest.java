package com.mcm.mscustomer.model.dto.request;

import com.mcm.mscustomer.model.enums.State;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddressRequest {

    @NotNull
    private State state;

    @NotEmpty
    private String city;

    @NotEmpty
    private String district;

    @NotEmpty
    private String street;

    @NotEmpty
    private String number;

    @NotEmpty
    private String cep;

    private String complement;

    @NotNull
    private String customerId;

}
