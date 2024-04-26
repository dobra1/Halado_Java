package com.miskolc.java.healthcareprovider.service.interfaces;

import com.miskolc.java.healthcareprovider.model.Treatment;
import com.miskolc.java.healthcareprovider.model.exeptions.TreatmentAlreadyExistException;
import com.miskolc.java.healthcareprovider.model.exeptions.TreatmentNotFoundException;

import java.util.HashSet;
import java.util.List;

public interface TreatmentService {
    Treatment getTreatment(int code) throws TreatmentNotFoundException;

    List<Treatment> getAllTreatment();

    void addTreatment(Treatment treatment) throws TreatmentAlreadyExistException;

    void updateTreatment(Treatment treatment) throws TreatmentNotFoundException;

    void deleteTreatment(int code);

    List<Treatment> getTreatmentByName(String name);


}
