package com.mcm.mscustomer.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mcm.mscustomer.model.enums.Sex;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerRequest {

    @Size(min = 3)
    @NotEmpty
    private String firstName;

    @Size(min = 3)
    @NotEmpty
    private String lastName;

    @NotNull
    private Sex sex;

    @CPF
    @JsonFormat(pattern = "^\\d{3}-\\d{3}-\\d{3}\\.\\d{2}$")
    @NotEmpty
    private String cpf;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdate;

    @Email
    @NotEmpty
    private String email;

    @Size(min = 6)
    @NotEmpty
    private String password;

}
