package com.miskolc.java.healthcareprovider.service.interfaces;

import com.miskolc.java.healthcareprovider.model.Doctor;
import com.miskolc.java.healthcareprovider.model.exeptions.DoctorAlreadyExistException;
import com.miskolc.java.healthcareprovider.model.exeptions.DoctorNotFoundException;

import java.util.List;

public interface DoctorService {
    Doctor getDoctor(int id) throws DoctorNotFoundException;
    List<Doctor> getAllDoctors();
    void addDoctor(Doctor doctor) throws DoctorAlreadyExistException;
    void updateDoctor(Doctor doctor) throws DoctorNotFoundException;
    void deleteDoctor(int id);
    List<Doctor> getDoctorsByPosition(String position);

}

