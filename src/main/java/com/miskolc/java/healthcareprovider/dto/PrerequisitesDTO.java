package com.miskolc.java.healthcareprovider.dto;

import lombok.Getter;


@Getter
public class PrerequisitesDTO {

    private int code;

    private String name;

    public PrerequisitesDTO(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
