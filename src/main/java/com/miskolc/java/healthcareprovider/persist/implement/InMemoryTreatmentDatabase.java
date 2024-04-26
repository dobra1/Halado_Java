package com.miskolc.java.healthcareprovider.persist.implement;

import com.miskolc.java.healthcareprovider.model.Doctor;
import com.miskolc.java.healthcareprovider.model.Prerequisites;
import com.miskolc.java.healthcareprovider.model.Treatment;
import com.miskolc.java.healthcareprovider.model.exeptions.PatientNotFoundException;
import com.miskolc.java.healthcareprovider.model.exeptions.TreatmentAlreadyExistException;
import com.miskolc.java.healthcareprovider.model.exeptions.TreatmentNotFoundException;
import com.miskolc.java.healthcareprovider.persist.interfaces.TreatmentRepository;
import org.springframework.stereotype.Component;

import java.time.Period;
import java.util.*;

@Component
public class InMemoryTreatmentDatabase implements TreatmentRepository {

    private final Map<Integer, Treatment> treatmentDb;

    public InMemoryTreatmentDatabase() {
        treatmentDb = new HashMap<>();
    }

    @Override
    public Treatment getTreatment(int code) throws TreatmentNotFoundException {
        Treatment treatment = treatmentDb.get(code);
        if (treatment != null) {
            return treatment;
        } else {
            throw new TreatmentNotFoundException();
        }
    }

    @Override
    public List<Treatment> getEveryTreatment() {
        List<Treatment> treatments = new ArrayList<>(treatmentDb.values());
        return treatments;
    }

    @Override
    public boolean idInUse(int code) {
        return treatmentDb.containsKey(code);
    }

    @Override
    public void addTreatment(Treatment treatment) throws TreatmentAlreadyExistException {
        if (!treatmentDb.containsKey(treatment.getCode())) {
            treatmentDb.put(treatment.getCode(), treatment);
        } else {
            throw new TreatmentAlreadyExistException();
        }
    }

    @Override
    public void updateTreatment(Treatment treatment) throws TreatmentNotFoundException {
        int treatmentCode = treatment.getCode();
        if (!treatmentDb.containsKey(treatmentCode)) {
            throw new TreatmentNotFoundException();
        }
        treatmentDb.put(treatmentCode, treatment);
    }

    @Override
    public void deleteTreatment(int code) {
        treatmentDb.remove(code);
    }


    @Override
    public Treatment findByCode(int code) {
        for (Treatment treatment : treatmentDb.values()) {
            if (treatment.getCode() == code) {
                return treatment;
            }
        }
        return null;
    }
}
