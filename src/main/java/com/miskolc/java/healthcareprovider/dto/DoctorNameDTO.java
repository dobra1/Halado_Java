package com.miskolc.java.healthcareprovider.dto;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class DoctorNameDTO {

    private int id;

    private int sealNumber;

    private String name;

    private String position;

    private List<PatientNameDTO> patients;

    public DoctorNameDTO(int id, int sealNumber, String name, String position) {
        this.id = id;
        this.sealNumber = sealNumber;
        this.name = name;
        this.position = position;
        patients = new ArrayList<>();
    }
}
