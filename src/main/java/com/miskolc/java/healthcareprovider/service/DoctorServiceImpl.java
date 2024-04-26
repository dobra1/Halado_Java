package com.miskolc.java.healthcareprovider.service;

import com.miskolc.java.healthcareprovider.dto.DoctorNameDTO;
import com.miskolc.java.healthcareprovider.model.Doctor;
import com.miskolc.java.healthcareprovider.model.Patient;
import com.miskolc.java.healthcareprovider.model.exeptions.DoctorAlreadyExistException;
import com.miskolc.java.healthcareprovider.model.exeptions.DoctorNotFoundException;
import com.miskolc.java.healthcareprovider.model.exeptions.PatientNotFoundException;
import com.miskolc.java.healthcareprovider.persist.interfaces.DoctorRepository;
import com.miskolc.java.healthcareprovider.persist.interfaces.TreatmentRepository;
import com.miskolc.java.healthcareprovider.service.interfaces.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor getDoctor(int id) throws DoctorNotFoundException {
        Doctor doctor = doctorRepository.getDoctor(id);
        return doctor;
    }

    @Override
    public List<Doctor> getAllDoctors() {

        return doctorRepository.getEveryDoctor();
    }

    @Override
    public void addDoctor(Doctor doctor) throws DoctorAlreadyExistException {
        if (doctorRepository.idInUse((doctor.getId()))) {
            throw new InvalidParameterException("Doctor alredy in use");
        }
        doctorRepository.addDoctor(doctor);
    }


    @Override
    public void updateDoctor(Doctor doctor) throws DoctorNotFoundException {
        if (!doctorRepository.idInUse(doctor.getId())) {
            throw new InvalidParameterException();
        }
        doctorRepository.updateDoctor(doctor);
    }

    @Override
    public void deleteDoctor(int id) {
        if (!doctorRepository.idInUse(id)) {
            throw new InvalidParameterException("Doctor with id " + id + " not found");
        }
        doctorRepository.deleteDoctor(id);
    }

    @Override
    public List<Doctor> getDoctorsByPosition(String position) {
        List<Doctor> doctorsWithPosition = new ArrayList<>();
        List<Doctor> allDoctors = doctorRepository.getEveryDoctor();
        for (Doctor doctor : allDoctors) {
            if (doctor.getPosition().equals(position)) {
                doctorsWithPosition.add(doctor);
            }
        }
        return doctorsWithPosition;
    }

}
