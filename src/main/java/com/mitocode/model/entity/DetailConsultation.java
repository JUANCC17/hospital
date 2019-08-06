package com.mitocode.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "details_consultation")
public class DetailConsultation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String diagnostic;

	private String treatment;
	
	@ManyToOne
	@JoinColumn(name = "medicalconsultation_id", nullable = false)
	private MedicalConsultation medicalConsultation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDiagnostic() {
		return diagnostic;
	}

	public void setDiagnostic(String diagnostic) {
		this.diagnostic = diagnostic;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public MedicalConsultation getMedicalConsultation() {
		return medicalConsultation;
	}

	public void setMedicalConsultation(MedicalConsultation medicalConsultation) {
		this.medicalConsultation = medicalConsultation;
	}
	
	
}
