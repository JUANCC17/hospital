package com.mitocode.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mitocode.model.entity.MedicalConsultation;
import com.mitocode.model.repository.MedicalConsultationRepository;
import com.mitocode.service.IMedicalConsultationService;

@Service
public class MedicalConsultationService implements IMedicalConsultationService {
	
	@Autowired
	private MedicalConsultationRepository medicalConsultationRepository;

	@Override
	public List<MedicalConsultation> getAll() throws Exception {
		return (List<MedicalConsultation>) medicalConsultationRepository.findAll();
	}

	@Override
	public Page<MedicalConsultation> getAll(Pageable pageable) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MedicalConsultation saveOrUpdate(MedicalConsultation entity) throws Exception {
		return medicalConsultationRepository.save(entity);
	}

	@Override
	public Optional<MedicalConsultation> getOne(Long id) throws Exception {
		return medicalConsultationRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		medicalConsultationRepository.deleteById(id);
		
	}

}
