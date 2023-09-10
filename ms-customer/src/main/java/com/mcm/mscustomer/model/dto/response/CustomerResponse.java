package com.mcm.mscustomer.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mcm.mscustomer.model.enums.Sex;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerResponse {

    private String id;

    private String firstName;

    private String lastName;

    private Sex sex;

    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdate;

    private String email;

    private String password;

    private boolean active;

}
