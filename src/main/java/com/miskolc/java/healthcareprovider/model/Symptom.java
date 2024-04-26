package com.miskolc.java.healthcareprovider.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Symptom {

    @Id
    private int id;

    private String name;

    @ManyToMany
    private List<Disease> diseases;
}
