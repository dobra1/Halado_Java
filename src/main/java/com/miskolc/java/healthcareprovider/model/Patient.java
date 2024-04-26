package com.miskolc.java.healthcareprovider.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient {

    @NotNull
    @Id
    private Integer id;

    private String name;

    private String locality;

    private LocalDate birthDate;

    @ManyToMany
    private List<Disease> diseases;

    @ManyToOne
    private Doctor doctors;
}
