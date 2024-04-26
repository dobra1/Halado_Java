package com.miskolc.java.healthcareprovider.persist.interfaces;

import com.miskolc.java.healthcareprovider.dto.DiseaseDTO;
import com.miskolc.java.healthcareprovider.model.Disease;
import com.miskolc.java.healthcareprovider.model.Doctor;
import com.miskolc.java.healthcareprovider.model.exeptions.DiseaseAlreadyExistException;
import com.miskolc.java.healthcareprovider.model.exeptions.DiseaseNotFoundException;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiseaseRepository {

    Disease getDisease(int code) throws DiseaseNotFoundException;
    List<Disease> getEveryDisease();
    boolean idInUse(int code);
    void addDisease(Disease disease) throws DiseaseAlreadyExistException;
    void updateDisease(Disease disease) throws DiseaseNotFoundException;
    void deleteDisease(int code);

}
