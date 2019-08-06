package com.mitocode.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.entity.MedicalConsultation;

public interface MedicalConsultationRepository extends JpaRepository<MedicalConsultation, Long> {

}
