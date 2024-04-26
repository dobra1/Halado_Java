package com.miskolc.java.healthcareprovider.service;

import com.miskolc.java.healthcareprovider.dto.DiseaseDTO;
import com.miskolc.java.healthcareprovider.model.Disease;
import com.miskolc.java.healthcareprovider.model.exeptions.DiseaseAlreadyExistException;
import com.miskolc.java.healthcareprovider.model.exeptions.DiseaseNotFoundException;
import com.miskolc.java.healthcareprovider.persist.interfaces.DiseaseRepository;
import com.miskolc.java.healthcareprovider.service.interfaces.DiseaseService;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiseaseServiceImpl implements DiseaseService {
    private DiseaseRepository diseaseRepository;

    public DiseaseServiceImpl(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    @Override
    public Disease getDisease(int id) throws DiseaseNotFoundException {
        return diseaseRepository.getDisease(id);
    }

    @Override
    public List<Disease> getAllDiseases() {
        return diseaseRepository.getEveryDisease();
    }

    @Override
    public void addDisease(Disease disease) throws DiseaseAlreadyExistException {
        if(diseaseRepository.idInUse((disease.getId()))) {
            throw new InvalidParameterException("Disease alredy in use");
        }
        diseaseRepository.addDisease(disease);
    }


    @Override
    public void updateDisease(Disease disease) throws DiseaseNotFoundException {
        int diseaseId = disease.getId();
        if (!diseaseRepository.idInUse(diseaseId)) {
            throw new InvalidParameterException("Disease is not found");
        }
        diseaseRepository.updateDisease(disease);
    }


    @Override
    public void deleteDisease(int id) {
        if (!diseaseRepository.idInUse(id)) {
            throw new InvalidParameterException("Disease with id " + id + " not found");
        }
        diseaseRepository.deleteDisease(id);
    }

    @Override
    public List<Disease> getDiseaseById(int id) {
        List<Disease> diseases = new ArrayList<>();
        List<Disease> allDiseases = diseaseRepository.getEveryDisease();
        for (Disease disease : allDiseases) {
            if (disease.getId() == id) {
                diseases.add(disease);
            }
        }
        return diseases;
    }

}