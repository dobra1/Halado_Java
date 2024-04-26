package com.miskolc.java.healthcareprovider.persist.implement;

import com.miskolc.java.healthcareprovider.dto.DiseaseDTO;
import com.miskolc.java.healthcareprovider.model.Disease;
import com.miskolc.java.healthcareprovider.model.Patient;
import com.miskolc.java.healthcareprovider.model.Symptom;
import com.miskolc.java.healthcareprovider.model.exeptions.DiseaseAlreadyExistException;
import com.miskolc.java.healthcareprovider.model.exeptions.DiseaseNotFoundException;
import com.miskolc.java.healthcareprovider.model.exeptions.DoctorAlreadyExistException;
import com.miskolc.java.healthcareprovider.model.exeptions.DoctorNotFoundException;
import com.miskolc.java.healthcareprovider.persist.interfaces.DiseaseRepository;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InMemoryDiseaseDatabase implements DiseaseRepository {
    private Map<Integer, Disease> diseaseDb;

    public InMemoryDiseaseDatabase() {
        diseaseDb = new HashMap<>();
        List<Symptom> symptoms = new ArrayList<>();
        List<Patient> patients = new ArrayList<>();
        diseaseDb.put(44, new Disease(44, "Covid19", "Lassu", symptoms, patients));
    }

    @Override
    public Disease getDisease(int id) throws DiseaseNotFoundException {
        if (diseaseDb.containsKey(id)) {
            return diseaseDb.get(id);
        }
        throw new DiseaseNotFoundException();
    }

    @Override
    public List<Disease> getEveryDisease() {
        List<Disease> diseases = new ArrayList<>(diseaseDb.values());
        return diseases;
    }

    @Override
    public boolean idInUse(int id) {
        return diseaseDb.containsKey(id);
    }

    @Override
    public void addDisease(Disease disease) throws DiseaseAlreadyExistException {
        if (!diseaseDb.containsKey(disease.getId())) {
            diseaseDb.put(disease.getId(), disease);
        } else {
            throw new DiseaseAlreadyExistException();
        }
    }

    @Override
    public void updateDisease(Disease disease) throws DiseaseNotFoundException {
        int diseaseId = disease.getId();
        if (!diseaseDb.containsKey(diseaseId)) {
            throw new DiseaseNotFoundException();
        }
        diseaseDb.put(diseaseId, disease);
    }

    @Override
    public void deleteDisease(int id) {
        diseaseDb.remove(id);
    }

}
