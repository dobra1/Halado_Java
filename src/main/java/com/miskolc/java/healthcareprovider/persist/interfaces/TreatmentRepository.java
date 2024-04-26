package com.miskolc.java.healthcareprovider.persist.interfaces;

import com.miskolc.java.healthcareprovider.model.Doctor;
import com.miskolc.java.healthcareprovider.model.Treatment;
import com.miskolc.java.healthcareprovider.model.exeptions.TreatmentAlreadyExistException;
import com.miskolc.java.healthcareprovider.model.exeptions.TreatmentNotFoundException;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TreatmentRepository  {
    Treatment getTreatment(int code) throws TreatmentNotFoundException;

    Treatment findByCode(int code);

    List<Treatment> getEveryTreatment();

    void addTreatment(Treatment treatment) throws TreatmentAlreadyExistException;

    boolean idInUse(int code);

    void updateTreatment(Treatment treatment) throws TreatmentNotFoundException;

    void deleteTreatment(int code);
}
