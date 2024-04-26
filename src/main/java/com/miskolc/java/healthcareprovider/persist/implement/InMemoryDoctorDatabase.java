package com.miskolc.java.healthcareprovider.persist.implement;

import com.miskolc.java.healthcareprovider.model.Doctor;
import com.miskolc.java.healthcareprovider.model.Patient;
import com.miskolc.java.healthcareprovider.model.Treatment;
import com.miskolc.java.healthcareprovider.model.exeptions.DoctorAlreadyExistException;
import com.miskolc.java.healthcareprovider.model.exeptions.DoctorNotFoundException;
import com.miskolc.java.healthcareprovider.model.exeptions.PatientAlreadyExistException;
import com.miskolc.java.healthcareprovider.model.exeptions.TreatmentNotFoundException;
import com.miskolc.java.healthcareprovider.persist.interfaces.DoctorRepository;
import com.miskolc.java.healthcareprovider.persist.interfaces.TreatmentRepository;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.util.*;

@Component
public class InMemoryDoctorDatabase implements DoctorRepository {

    private final Map<Integer, Doctor> doctorDb;

    public InMemoryDoctorDatabase() {
        doctorDb = new HashMap<>();
    }

    @Override
    public Doctor getDoctor(int id) throws DoctorNotFoundException {
        Doctor doctor = doctorDb.get(id);
        if (doctor != null) {
            return doctor;
        }
        throw new DoctorNotFoundException();
    }
    @Override
    public List<Doctor> getEveryDoctor() {
        List<Doctor> doctors = new ArrayList<>(doctorDb.values());
        return doctors;
    }


    @Override
    public boolean idInUse(int id) {
        return doctorDb.containsKey(id);
    }

    @Override
    public void addDoctor(Doctor doctor) throws DoctorAlreadyExistException {
        if (!doctorDb.containsKey(doctor.getId())) {
            doctorDb.put(doctor.getId(), doctor);
        } else {
            throw new DoctorAlreadyExistException();
        }
    }

    @Override
    public void updateDoctor(Doctor doctor) throws DoctorNotFoundException {
        int doctorId = doctor.getId();
        if (!doctorDb.containsKey(doctorId)) {
            throw new DoctorNotFoundException();
        }
        doctorDb.put(doctorId, doctor);
    }

    @Override
    public void deleteDoctor(int doctorId) {
        doctorDb.remove(doctorId);

    }

}
