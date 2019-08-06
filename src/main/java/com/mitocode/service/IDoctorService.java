package com.mitocode.service;

import java.util.Collection;
import java.util.List;

import com.mitocode.model.entity.Doctor;

public interface IDoctorService extends CrudService<Doctor> {
	Collection<Doctor> findDoctorByIdWithSpecialty();
	
	List<Doctor> findDoctorsBySpecialtyId(Long specialtyId);
}
