package com.miskolc.java.healthcareprovider.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prerequisites {


    @Id
    private int code;

    private String name;

    @ManyToOne
    private Treatment treatment;
}
