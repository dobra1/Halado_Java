package com.miskolc.java.healthcareprovider.dto;

import lombok.Getter;

@Getter
public class SymptomDTO {

    private int id;

    private String name;

    public SymptomDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
