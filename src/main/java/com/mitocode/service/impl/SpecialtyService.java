package com.mitocode.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mitocode.model.entity.Specialty;
import com.mitocode.model.repository.SpecialtyRepository;
import com.mitocode.service.ISpecialtyService;

@Service
public class SpecialtyService implements ISpecialtyService {

	@Autowired
	private SpecialtyRepository specialtyRepository;

	@Override
	public List<Specialty> getAll() throws Exception {
		return specialtyRepository.findAll();
	}

	@Override
	public Page<Specialty> getAll(Pageable pageable) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Specialty saveOrUpdate(Specialty entity) throws Exception {
		return specialtyRepository.save(entity);
	}

	@Override
	public Optional<Specialty> getOne(Long id) throws Exception {
		return specialtyRepository.findById(id);
	}

	@Override
	@Transactional
	public void deleteById(Long id) throws Exception {
		specialtyRepository.deleteById(id);

	}


}
