package com.mitocode.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mitocode.model.entity.MedicalConsultation;

public interface MedicalConsultationRepository extends JpaRepository<MedicalConsultation, Long> {

	
	@Query(value = "SELECT * FROM medical_consultations m JOIN patients p on p.id = m.patient_id JOIN doctors d on d.id = m.doctor_id JOIN details_consultation dc on dc.medicalconsultation_id = m.id WHERE m.id = ?1",nativeQuery = true)
	Optional<MedicalConsultation> findMedicalConsultationByIdWithPatientWithDoctorWithItemMedicalConsultation(Long medicalConsultationId);
	
	@Query(value = "SELECT * FROM medical_consultations m WHERE m.patient_id = ?1",nativeQuery = true)
	List<MedicalConsultation> findMedicalConsultationsByPatientId(Long patientId);
	
	@Query(value = "SELECT * FROM medical_consultations mc JOIN patients p on mc.patient_id = p.id JOIN details_consultation dc on dc.medicalconsultation_id = mc.id WHERE mc.patient_id = ?1",nativeQuery = true)
	List<MedicalConsultation> findHistoriaClinicaByPatientId(Long patientId);
	
}
