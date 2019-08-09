package com.mitocode.service;

import java.util.List;
import java.util.Optional;

import com.mitocode.model.entity.MedicalConsultation;

public interface IMedicalConsultationService extends CrudService<MedicalConsultation> {

	Optional<MedicalConsultation> findMedicalConsultationByIdWithPatientWithDoctorWithItemMedicalConsultation(Long medicalConsultationId);
	
	List<MedicalConsultation> findMedicalConsultationsByPatientId(Long patientId);
}
