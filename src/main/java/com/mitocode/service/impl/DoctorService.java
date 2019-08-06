package com.mitocode.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mitocode.model.entity.Doctor;
import com.mitocode.model.repository.DoctorRepository;
import com.mitocode.service.IDoctorService;

@Service
public class DoctorService implements IDoctorService {

	@Autowired
	private DoctorRepository doctorRepository;
	
	@Override
	public List<Doctor> getAll() throws Exception {
		return (List<Doctor>) doctorRepository.findAll() ;
	}

	@Override
	public Page<Doctor> getAll(Pageable pageable) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Doctor saveOrUpdate(Doctor entity) throws Exception {
		return doctorRepository.save(entity);
	}

	@Override
	public Optional<Doctor> getOne(Long id) throws Exception {
		return doctorRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		doctorRepository.deleteById(id);
		
	}

	@Override
	public Collection<Doctor> findDoctorByIdWithSpecialty() {
		
		return doctorRepository.findDoctorByIdWithSpecialty();
	}

	@Override
	public List<Doctor> findDoctorsBySpecialtyId(Long specialtyId) {
		return doctorRepository.findDoctorsBySpecialtyId(specialtyId);
	}







}
