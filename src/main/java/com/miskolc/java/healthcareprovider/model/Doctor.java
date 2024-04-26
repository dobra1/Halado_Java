package com.miskolc.java.healthcareprovider.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.miskolc.java.healthcareprovider.model.Patient;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Doctor {

    @NotNull
    @Id
    private int id;

    private int sealNumber;

    @NotBlank
    private String name;

    @Min(500000)
    private int salary;

    private String position;

    @ManyToMany
    private List<Treatment> treatments;

    @OneToMany
    private List<Patient> patients;


}
