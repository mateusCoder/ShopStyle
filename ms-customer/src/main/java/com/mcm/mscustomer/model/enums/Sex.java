package com.mcm.mscustomer.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Sex {
    FEMININO("Feminino"),
    MASCULINO("Masculino");

    private final String value;

    Sex(String value){
        this.value = value;
    }

    @JsonCreator
    public static Sex fromValue(String value){
        for (Sex sex : Sex.values()){
            if (sex.value.equalsIgnoreCase(value)){
                return sex;
            }
        }
        return null;
    }
}
