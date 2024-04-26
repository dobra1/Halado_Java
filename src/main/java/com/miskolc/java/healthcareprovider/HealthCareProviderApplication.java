package com.miskolc.java.healthcareprovider;

import com.miskolc.java.healthcareprovider.model.*;
import com.miskolc.java.healthcareprovider.model.exeptions.DiseaseAlreadyExistException;
import com.miskolc.java.healthcareprovider.model.exeptions.DoctorAlreadyExistException;
import com.miskolc.java.healthcareprovider.model.exeptions.PatientAlreadyExistException;
import com.miskolc.java.healthcareprovider.model.exeptions.TreatmentAlreadyExistException;
import com.miskolc.java.healthcareprovider.persist.interfaces.DiseaseRepository;
import com.miskolc.java.healthcareprovider.persist.interfaces.DoctorRepository;
import com.miskolc.java.healthcareprovider.persist.interfaces.PatientRepository;
import com.miskolc.java.healthcareprovider.persist.interfaces.TreatmentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;

@SpringBootApplication
public class HealthCareProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthCareProviderApplication.class, args);
    }

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TreatmentRepository treatmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DiseaseRepository diseaseRepository;

    @PostConstruct
    public void generateData() {

        Prerequisites prerequisite = new Prerequisites(1, "Előfeltétel neve", new Treatment());
        Symptom symptom = new Symptom(100, "Magass laz", new ArrayList<>());
        Symptom virus = new Symptom(101, "Alacsony Laz", new ArrayList<>());
        Treatment vaccine = new Treatment(22, "Vedooltas", "gyors folyamat", Period.ofMonths(1), new ArrayList<>(), new HashSet<>());
        Doctor doctor = new Doctor(2, 2230, "Dr. Dobra", 856000, "Emergency", new ArrayList<>(), new ArrayList<>());
        Patient patient = new Patient(3, "Peti", "Tokaj", LocalDate.of(1978, 10, 12), new ArrayList<>(), new Doctor());
        Disease disease = new Disease(33, "Covid19", "Lassu", new ArrayList<>(), new ArrayList<>());

        vaccine.getDoctors().add(doctor);
        doctor.getTreatments().add(vaccine);
        vaccine.getPrerequisites().add(prerequisite);
        disease.getSymptoms().add(symptom);
        disease.getSymptoms().add(virus);


        try {
            treatmentRepository.addTreatment(vaccine);
            doctorRepository.addDoctor(doctor);
            patientRepository.addPatient(patient);
            diseaseRepository.addDisease(disease);
        } catch (DoctorAlreadyExistException | TreatmentAlreadyExistException | PatientAlreadyExistException |
                 DiseaseAlreadyExistException e) {
            throw new RuntimeException(e);
        }
    }

    }
