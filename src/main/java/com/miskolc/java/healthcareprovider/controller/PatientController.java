package com.miskolc.java.healthcareprovider.controller;

import com.miskolc.java.healthcareprovider.dto.*;
import com.miskolc.java.healthcareprovider.model.*;
import com.miskolc.java.healthcareprovider.model.exeptions.PatientAlreadyExistException;
import com.miskolc.java.healthcareprovider.model.exeptions.PatientNotFoundException;
import com.miskolc.java.healthcareprovider.service.interfaces.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PatientController {
    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping(value = "getPatient", method = RequestMethod.GET)
    @ResponseBody
    public Patient getPatient(@RequestParam("id") int id) throws PatientNotFoundException {
        return patientService.getPatient(id);
    }

    @RequestMapping("GetEveryPatient")
    public List<PatientNameDTO> getEveryPatient() {

        List<Patient> patients = patientService.getAllPatients();
        List<PatientNameDTO> patientNameDTOS = new ArrayList<>();

        for (Patient patient : patients) {
            PatientNameDTO patientNameDTO =
                    new PatientNameDTO(patient.getId(), patient.getName(), patient.getLocality(), patient.getBirthDate());

            for (Disease disease: patient.getDiseases())   {
                patientNameDTO.getDiseases().add(new DiseaseDTO(disease.getId(), disease.getName(), disease.getProcess()));
            }

            Doctor doctor = patient.getDoctors();
            if (doctor != null) {
                patientNameDTO.getDoctors().add(new DoctorDTO(doctor.getId(), doctor.getName(), doctor.getPosition()));
            }

            patientNameDTOS.add(patientNameDTO);
        }

        return patientNameDTOS;
    }



    @PostMapping("AddPatient")
    public void addPatient(@RequestBody Patient patient) throws PatientNotFoundException, PatientAlreadyExistException {
        patientService.addPatient(patient);
    }

    @PostMapping("UpdatePatient")
    public void updatePatient(@RequestBody Patient patient) throws PatientNotFoundException {
            patientService.updatePatient(patient);
    }


    @DeleteMapping("DeletePatient")
    public void deletePatient(@RequestParam("id") int id) throws PatientNotFoundException {
            patientService.removePatient(id);
    }

    @GetMapping("getPatientByLocality")
    public List<Patient> getPatientByLocality(@RequestParam("locality") String locality) {
        return patientService.getPatientByLocality(locality);
    }
}
