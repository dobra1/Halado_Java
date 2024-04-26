package com.miskolc.java.healthcareprovider.service;

import com.miskolc.java.healthcareprovider.model.Treatment;
import com.miskolc.java.healthcareprovider.model.exeptions.TreatmentAlreadyExistException;
import com.miskolc.java.healthcareprovider.model.exeptions.TreatmentNotFoundException;
import com.miskolc.java.healthcareprovider.persist.interfaces.TreatmentRepository;
import com.miskolc.java.healthcareprovider.service.interfaces.TreatmentService;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TreatmentServiceImpl implements TreatmentService {

    private final TreatmentRepository treatmentRepository;

    public TreatmentServiceImpl(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    @Override
    public Treatment getTreatment(int code) throws TreatmentNotFoundException {
        return treatmentRepository.getTreatment(code);
    }

    @Override
    public List<Treatment> getAllTreatment() {
        return treatmentRepository.getEveryTreatment();
    }

    @Override
    public void addTreatment(Treatment treatment) throws TreatmentAlreadyExistException {
        if (treatmentRepository.idInUse(treatment.getCode())) {
            throw new InvalidParameterException();
        }
        treatmentRepository.addTreatment(treatment);
    }

    @Override
    public void updateTreatment(Treatment treatment) throws TreatmentNotFoundException {
        if (!treatmentRepository.idInUse(treatment.getCode())) {
            throw new InvalidParameterException();
        }
        treatmentRepository.updateTreatment(treatment);
    }

    @Override
    public void deleteTreatment(int code) {
        if (!treatmentRepository.idInUse(code)) {
            throw new InvalidParameterException();
        }
        treatmentRepository.deleteTreatment(code);
    }

    @Override
    public List<Treatment> getTreatmentByName(String name) {
        List<Treatment> treatmentsWithName = new ArrayList<>();
        List<Treatment> allTreatments = treatmentRepository.getEveryTreatment();
        for (Treatment treatment : allTreatments) {
            if (treatment.getName().equals(name)) {
                treatmentsWithName.add(treatment);
            }
        }
        return treatmentsWithName;
    }
}
