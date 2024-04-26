package com.miskolc.java.healthcareprovider.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.convert.PeriodFormat;

import java.time.Period;
import java.util.HashSet;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Treatment {

    @Id
    private Integer code;

    private String name;

    private String description;

    private Period period;

    @ManyToMany
    private List<Doctor> doctors;

    @OneToMany
    private HashSet<Prerequisites> prerequisites;


}
