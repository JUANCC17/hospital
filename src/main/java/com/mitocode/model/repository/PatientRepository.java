package com.mitocode.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitocode.model.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{


}
