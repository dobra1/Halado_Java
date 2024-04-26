package com.miskolc.java.healthcareprovider.service;

import com.miskolc.java.healthcareprovider.model.Patient;
import com.miskolc.java.healthcareprovider.model.exeptions.PatientAlreadyExistException;
import com.miskolc.java.healthcareprovider.persist.interfaces.PatientRepository;
import com.miskolc.java.healthcareprovider.model.exeptions.PatientNotFoundException;
import com.miskolc.java.healthcareprovider.service.interfaces.PatientService;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient getPatient(int id) throws PatientNotFoundException {
        Patient patient = patientRepository.getPatient(id);
        return patient;
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.getEveryPatient();
    }

    @Override
    public void addPatient(Patient patient) throws PatientAlreadyExistException {
        if (patientRepository.idInUse(patient.getId())) {
            throw new PatientAlreadyExistException();
        }
        patientRepository.addPatient(patient);
    }



    @Override
    public void updatePatient(Patient patient) throws PatientNotFoundException {
        if (!patientRepository.idInUse(patient.getId())) {
            throw new InvalidParameterException();
        }
        patientRepository.updatePatient(patient);
    }

    @Override
    public void removePatient(int id) throws PatientNotFoundException {
        if (!patientRepository.idInUse(id)) {
            throw new PatientNotFoundException();
        }
        patientRepository.removePatient(id);
    }


    @Override
    public List<Patient> getPatientByLocality(String locality) {
        List<Patient> patientsWithLocality = new ArrayList<>();
        List<Patient> allPatients = patientRepository.getEveryPatient();
        for (Patient patient : allPatients) {
            if (patient.getLocality().equals(locality)) {
                patientsWithLocality.add(patient);
            }
        }
        return patientsWithLocality;
    }

}
