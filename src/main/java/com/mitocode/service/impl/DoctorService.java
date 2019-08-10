package com.mitocode.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
		return doctorRepository.findAll();
	}

	@Override
	public Page<Doctor> getAll(Pageable pageable) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Doctor saveOrUpdate(Doctor entity) throws Exception {
		return doctorRepository.save(entity);
	}

	@Override
	public Optional<Doctor> getOne(Long id) throws Exception {
		return doctorRepository.findById(id);
	}

	@Override
	@Transactional
	public void deleteById(Long id) throws Exception {
		doctorRepository.deleteById(id);

	}

	@Override
	public List<Doctor> findDoctorsBySpecialtyId(Long specialtyId) {
		return doctorRepository.findDoctorsBySpecialtyId(specialtyId);
	}

	@Override
	public Collection<Doctor> findDoctorByIdWithSpecialty() {
		return doctorRepository.findDoctorByIdWithSpecialty();
	}

}
