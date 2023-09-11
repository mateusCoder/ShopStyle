package com.mcm.mscustomer.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mcm.mscustomer.model.enums.Sex;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Random;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UniqueCPFAndEmail",
        columnNames = { "cpf", "email" }) })
public class Customer {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    @Enumerated(value = EnumType.STRING)
    private Sex sex;

    private String cpf;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate birthdate;

    private String email;

    private String password;

    private boolean active = true;

    private static final int ID_LENGTH = 10;
    private static final String CHARACTERS = "0123456789abcdefghijklmnopqrstuvwxyz";

    @PrePersist
    private void generateId(){
        Random random = new Random();
        StringBuilder id = new StringBuilder(ID_LENGTH);

        for (int i = 0; i < ID_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            id.append(randomChar);
        }
        this.id = id.toString();
    }

}
