package com.miskolc.java.healthcareprovider.controller;

import com.miskolc.java.healthcareprovider.dto.*;
import com.miskolc.java.healthcareprovider.model.*;
import com.miskolc.java.healthcareprovider.model.exeptions.DiseaseAlreadyExistException;
import com.miskolc.java.healthcareprovider.model.exeptions.DiseaseNotFoundException;
import com.miskolc.java.healthcareprovider.service.interfaces.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DiseaseController {
    private final DiseaseService diseaseService;

    @Autowired
    public DiseaseController(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    @RequestMapping(value = "getDisease", method = RequestMethod.GET)
    @ResponseBody
    public Disease getDisease(@RequestParam("id") int id) throws DiseaseNotFoundException {
        return diseaseService.getDisease(id);
    }

    @GetMapping("GetEveryDisease")
    public List<DiseaseDTO> getEveryDisease() {

        List<Disease> diseases = diseaseService.getAllDiseases();
        List<DiseaseDTO> diseaseDTOS = new ArrayList<>();

        for (Disease disease : diseases) {
            DiseaseDTO diseaseDTO =
                    new DiseaseDTO(disease.getId(), disease.getName(), disease.getProcess());
            for (Symptom symptom: disease.getSymptoms()) {
                diseaseDTO.getSymptoms().add(new SymptomDTO(symptom.getId(), symptom.getName()));
            }

            diseaseDTOS.add(diseaseDTO);
        }

        return diseaseDTOS;
    }

    @PostMapping("AddDisease")
    public void addDisease(@RequestBody Disease disease) throws DiseaseAlreadyExistException {
        diseaseService.addDisease(disease);
    }

    @PostMapping("UpdateDisease")
    public void updateDisease(@RequestBody Disease disease) throws DiseaseNotFoundException {
            diseaseService.updateDisease(disease);
    }

    @RequestMapping(value = "DeleteDisease", method = RequestMethod.DELETE)
    public void deleteDisease(@RequestParam("id") int id) {
        diseaseService.deleteDisease(id);
    }

    @GetMapping("getDiseaseById")
    public List<Disease> getDiseasesById(@RequestParam("id") int id) {
        return diseaseService.getDiseaseById(id);
    }
}
