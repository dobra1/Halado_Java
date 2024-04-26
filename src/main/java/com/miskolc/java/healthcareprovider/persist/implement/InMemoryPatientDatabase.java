package com.miskolc.java.healthcareprovider.persist.implement;

import com.miskolc.java.healthcareprovider.model.Disease;
import com.miskolc.java.healthcareprovider.model.Doctor;
import com.miskolc.java.healthcareprovider.model.Patient;
import com.miskolc.java.healthcareprovider.model.exeptions.DiseaseAlreadyExistException;
import com.miskolc.java.healthcareprovider.model.exeptions.DoctorNotFoundException;
import com.miskolc.java.healthcareprovider.model.exeptions.PatientAlreadyExistException;
import com.miskolc.java.healthcareprovider.model.exeptions.PatientNotFoundException;
import com.miskolc.java.healthcareprovider.persist.interfaces.PatientRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryPatientDatabase implements PatientRepository {
    private Map<Integer, Patient> patientDb;

    public InMemoryPatientDatabase() {
        patientDb = new HashMap<>();
        List<Disease> diseases = new ArrayList<>();
        Doctor doctor = new Doctor();
        patientDb.put(1, new Patient(1, "Józsi 1", "Debrecen",
                LocalDate.of(1991, 1, 12), diseases, doctor));
    }

    @Override
    public Patient getPatient(int id) throws PatientNotFoundException {
        if (patientDb.containsKey(id)) {
            return patientDb.get(id);
        }
        throw new PatientNotFoundException();
    }

    @Override
    public List<Patient> getEveryPatient() {
        List<Patient> patients = new ArrayList<>(patientDb.values());
        return patients;
    }

    @Override
    public boolean idInUse(int id) {
        return patientDb.containsKey(id);
    }

    @Override
    public void addPatient(Patient patient) throws PatientAlreadyExistException {
        if (!patientDb.containsKey(patient.getId())) {
            patientDb.put(patient.getId(), patient);
        } else {
            throw new PatientAlreadyExistException();
        }
    }
    @Override
    public void updatePatient(Patient patient) throws PatientNotFoundException {
        int patientId = patient.getId();
        if (!patientDb.containsKey(patientId)) {
            throw new PatientNotFoundException();
        }
        patientDb.put(patientId, patient);
    }

    @Override
    public void removePatient(int patientId) {
        patientDb.remove(patientId);

    }

}
