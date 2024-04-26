package com.miskolc.java.healthcareprovider.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.miskolc.java.healthcareprovider.model.Doctor;
import com.miskolc.java.healthcareprovider.model.Prerequisites;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Getter
public class TreatmentDTO {

    private Integer code;

    private String name;

    private String description;

    private Period period;

    private HashSet<PrerequisitesDTO> prerequisites;

    public TreatmentDTO(Integer code, String name, String description, Period period) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.period = period;
        prerequisites = new HashSet<>();
    }
}
