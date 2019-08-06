package com.mitocode.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mitocode.model.entity.Patient;
import com.mitocode.model.repository.PatientRepository;
import com.mitocode.service.IPatientService;

@Service
public class PatientService implements IPatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Override
	public List<Patient> getAll() throws Exception {
		return (List<Patient>) patientRepository.findAll();
	}

	@Override
	public Page<Patient> getAll(Pageable pageable) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Patient saveOrUpdate(Patient entity) throws Exception {
		return patientRepository.save(entity);
	}

	@Override
	public Optional<Patient> getOne(Long id) throws Exception {
		return patientRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		patientRepository.deleteById(id);

	}

}
