package com.mitocode.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "patients")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Ingrese el nombre del paciente")
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@NotEmpty(message = "Ingrese el apellido del paciente")
	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Size(min = 8, max = 8)
	@NotEmpty(message = "Ingrese el DNI del paciente")
	@Column(nullable = false)
	private String dni;

	@Column(name = "number_clinical_history")
	private String numberClinicalHistory;

	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<MedicalConsultation> medicalDetails;

	public Patient() {
		this.medicalDetails = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNumberClinicalHistory() {
		return numberClinicalHistory;
	}

	public void setNumberClinicalHistory(String numberClinicalHistory) {
		this.numberClinicalHistory = numberClinicalHistory;
	}

	public List<MedicalConsultation> getMedicalDetails() {
		return medicalDetails;
	}

	public void setMedicalDetails(List<MedicalConsultation> medicalDetails) {
		this.medicalDetails = medicalDetails;
	}

}
