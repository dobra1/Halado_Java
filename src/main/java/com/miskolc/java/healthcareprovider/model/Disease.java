package com.miskolc.java.healthcareprovider.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Disease {

    @NotNull
    @Id
    private Integer id;

    private String name;

    private String process;

    @ManyToMany
    private List<Symptom> symptoms;

    @ManyToMany
    private List<Patient> patients;
}
