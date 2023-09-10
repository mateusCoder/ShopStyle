package com.mcm.mscustomer.model.enums;

public enum Sex {
    FEMININO ("Feminino"),
    MASCULINO ("Masculino");

    private final String values;

    Sex(String values){
        this.values = values;
    }

    public static String getSex(String value){
        for (Sex sex : Sex.values()){
            if (sex.values.equals(value)){
                return value;
            }
        }
        return null;
    }
}
