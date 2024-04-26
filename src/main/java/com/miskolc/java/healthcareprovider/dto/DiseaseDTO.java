package com.miskolc.java.healthcareprovider.dto;

import com.miskolc.java.healthcareprovider.model.Symptom;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DiseaseDTO {

    private Integer id;

    private String name;

    private String process;

    private List<SymptomDTO> symptoms;

    public DiseaseDTO(Integer id, String name, String process) {
        this.id = id;
        this.name = name;
        this.process = process;
        symptoms = new ArrayList<>();
    }
}
