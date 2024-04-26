package com.miskolc.java.healthcareprovider.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PatientNameDTO {

    private Integer id;

    private String name;

    private String locality;

    private LocalDate birthDate;

    private List<DiseaseDTO> diseases;

    private List<DoctorDTO> doctors;


    public PatientNameDTO(Integer id, String name, String locality, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.locality = locality;
        this.birthDate = birthDate;
        diseases = new ArrayList<>();
        doctors = new ArrayList<>();
    }
}
