package com.miskolc.java.healthcareprovider.controller;

import com.miskolc.java.healthcareprovider.dto.DoctorNameDTO;
import com.miskolc.java.healthcareprovider.dto.PatientNameDTO;
import com.miskolc.java.healthcareprovider.model.Doctor;
import com.miskolc.java.healthcareprovider.model.Patient;
import com.miskolc.java.healthcareprovider.model.exeptions.DoctorAlreadyExistException;
import com.miskolc.java.healthcareprovider.model.exeptions.DoctorNotFoundException;
import com.miskolc.java.healthcareprovider.service.interfaces.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@RestController
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @RequestMapping(value = "getDoctor", method = RequestMethod.GET)
    @ResponseBody
    public Doctor getDoctor(@RequestParam("id") int id) throws DoctorNotFoundException {
        return doctorService.getDoctor(id);
    }


    @GetMapping("GetEveryDoctor")
    public List<DoctorNameDTO> getEveryDoctor() {

        List<Doctor> doctors = doctorService.getAllDoctors();
        List<DoctorNameDTO> doctorNameDTOS = new ArrayList<>();

        for (Doctor doctor : doctors) {
            DoctorNameDTO doctorNameDTO =
                    new DoctorNameDTO(doctor.getId(), doctor.getSealNumber(), doctor.getName(), doctor.getPosition());


            if (doctor.getPatients() != null) {
                for (Patient patient : doctor.getPatients()) {
                    doctorNameDTO.getPatients().add(new PatientNameDTO(patient.getId(), patient.getName(), patient.getLocality(), patient.getBirthDate()));
                }
            }

            doctorNameDTOS.add(doctorNameDTO);
        }


        return doctorNameDTOS;
    }


    @PostMapping("AddDoctor")
    public void addDoctor(@RequestBody Doctor doctor) throws DoctorAlreadyExistException {
        doctorService.addDoctor(doctor);
    }

    @PostMapping("UpdateDoctor")
    public void updateDoctor(@RequestBody Doctor doctor) throws DoctorNotFoundException {
        doctorService.updateDoctor(doctor);
    }

    @RequestMapping(value = "DeleteDoctor", method = RequestMethod.DELETE)
    public void deleteDoctor(@RequestParam("id") int id) {
        doctorService.deleteDoctor(id);
    }

    @GetMapping("getDoctorsByPosition")
    public List<Doctor> getDoctorsByPosition(@RequestParam("position") String position) {
       return doctorService.getDoctorsByPosition(position);

    }
}