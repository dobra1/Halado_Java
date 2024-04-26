package com.miskolc.java.healthcareprovider.controller;

import com.miskolc.java.healthcareprovider.dto.*;
import com.miskolc.java.healthcareprovider.model.*;
import com.miskolc.java.healthcareprovider.model.exeptions.DoctorAlreadyExistException;
import com.miskolc.java.healthcareprovider.model.exeptions.PatientNotFoundException;
import com.miskolc.java.healthcareprovider.model.exeptions.TreatmentAlreadyExistException;
import com.miskolc.java.healthcareprovider.model.exeptions.TreatmentNotFoundException;
import com.miskolc.java.healthcareprovider.service.interfaces.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TreatmentController {

    private TreatmentService treatmentService;

    @Autowired
    public TreatmentController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @RequestMapping(value = "getTreatment", method = RequestMethod.GET)
    @ResponseBody
    public Treatment getTreatment(@RequestParam("code") int code) throws TreatmentNotFoundException {
            return treatmentService.getTreatment(code);
    }

    @RequestMapping(value = "GetEveryTreatment")
    public List<TreatmentDTO> getEveryTreatment() {

        List<Treatment> treatments = treatmentService.getAllTreatment();
        List<TreatmentDTO> treatmentDTOS = new ArrayList<>();

        for (Treatment treatment : treatments) {
            TreatmentDTO treatmentDTO = new TreatmentDTO(
                    treatment.getCode(),
                    treatment.getName(),
                    treatment.getDescription(),
                    treatment.getPeriod());


            for (Prerequisites prerequisite : treatment.getPrerequisites()) {
                treatmentDTO.getPrerequisites().add(new PrerequisitesDTO(
                        prerequisite.getCode(),
                        prerequisite.getName()));
            }

            treatmentDTOS.add(treatmentDTO);
        }

        return treatmentDTOS;
    }

    @RequestMapping(value = "AddTreatment", method = RequestMethod.POST)
    public void addTreatment(@RequestBody Treatment treatment) throws TreatmentAlreadyExistException {
        treatmentService.addTreatment(treatment);
    }

    @PostMapping("UpdateTreatment")
    public void updateTreatment(@RequestBody Treatment treatment) throws TreatmentNotFoundException {
        treatmentService.updateTreatment(treatment);
    }

    @RequestMapping(value="DeleteTreatment", method = RequestMethod.DELETE)
    public void deleteTreatment(@RequestParam("code") int code) {
        treatmentService.deleteTreatment(code);
    }

    @GetMapping("getTreatmentByName")
    public List<Treatment> getTreatmentByName(@RequestParam("name") String name) {
         return treatmentService.getTreatmentByName(name);
    }
}

