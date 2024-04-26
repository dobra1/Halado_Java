package com.miskolc.java.healthcareprovider.persist.interfaces;

import com.miskolc.java.healthcareprovider.dto.DoctorNameDTO;
import com.miskolc.java.healthcareprovider.model.Doctor;
import com.miskolc.java.healthcareprovider.model.exeptions.DoctorAlreadyExistException;
import com.miskolc.java.healthcareprovider.model.exeptions.DoctorNotFoundException;
import com.miskolc.java.healthcareprovider.model.exeptions.TreatmentNotFoundException;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DoctorRepository  {
    void addDoctor(Doctor doctor) throws DoctorAlreadyExistException;
    Doctor getDoctor(int id) throws DoctorNotFoundException;
    List<Doctor> getEveryDoctor();
    boolean idInUse(int id);
    void updateDoctor(Doctor doctor) throws DoctorNotFoundException;
    void deleteDoctor(int id);
}
