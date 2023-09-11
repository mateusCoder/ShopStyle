package com.mcm.mscustomer.model.entities;

import com.mcm.mscustomer.model.enums.State;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private State state;

    private String city;

    private String district;

    private String street;

    private String number;

    private String cep;

    private String complement;

    private String customerId;

}
