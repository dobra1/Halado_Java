package com.miskolc.java.healthcareprovider.service.interfaces;

import com.miskolc.java.healthcareprovider.dto.DiseaseDTO;
import com.miskolc.java.healthcareprovider.model.Disease;
import com.miskolc.java.healthcareprovider.model.exeptions.DiseaseAlreadyExistException;
import com.miskolc.java.healthcareprovider.model.exeptions.DiseaseNotFoundException;

import java.util.List;

public interface DiseaseService {
    Disease getDisease(int code) throws DiseaseNotFoundException;

    List<Disease> getAllDiseases();
    void addDisease(Disease disease) throws DiseaseAlreadyExistException;

    void updateDisease(Disease disease) throws DiseaseNotFoundException;

    void deleteDisease(int id);

    List<Disease> getDiseaseById(int id);

}
