package com.miskolc.java.healthcareprovider.service.interfaces;

import com.miskolc.java.healthcareprovider.model.Patient;
import com.miskolc.java.healthcareprovider.model.exeptions.PatientAlreadyExistException;
import com.miskolc.java.healthcareprovider.model.exeptions.PatientNotFoundException;

import java.util.List;

public interface PatientService {
    Patient getPatient(int id) throws PatientNotFoundException;
    List<Patient> getAllPatients();

    void addPatient(Patient patient) throws PatientNotFoundException, PatientAlreadyExistException;
    
    void updatePatient(Patient patient) throws PatientNotFoundException;

    void removePatient(int id) throws PatientNotFoundException;

    List<Patient> getPatientByLocality(String locality);
}
