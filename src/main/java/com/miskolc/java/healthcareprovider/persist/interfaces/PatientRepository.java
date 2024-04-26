package com.miskolc.java.healthcareprovider.persist.interfaces;

import com.miskolc.java.healthcareprovider.model.Doctor;
import com.miskolc.java.healthcareprovider.model.Patient;
import com.miskolc.java.healthcareprovider.model.exeptions.PatientAlreadyExistException;
import com.miskolc.java.healthcareprovider.model.exeptions.PatientNotFoundException;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PatientRepository  {
    Patient getPatient(int id) throws PatientNotFoundException;

    List<Patient> getEveryPatient();

    void addPatient(Patient patient) throws PatientAlreadyExistException;

    boolean idInUse(int id);

    void removePatient(int id) throws PatientNotFoundException;

    void updatePatient(Patient patient) throws PatientNotFoundException;

}
