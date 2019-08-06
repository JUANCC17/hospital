package com.mitocode.model.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;


@Entity
@Table(name = "medical_consultations")
public class MedicalConsultation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "create_at")
	private LocalDate createAt;
	
	@ManyToOne
	@JoinColumn(name = "doctor_id", nullable = false)
	private Doctor doctor;
	
	@ManyToOne
	@JoinColumn(name = "patient_id", nullable = false)
	private Patient patient;

	@OneToMany(mappedBy = "medicalConsultation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<DetailConsultation> items;

	public MedicalConsultation() {
		this.items = new ArrayList<>();
	}

	@PrePersist
	public void prePersist() {
		createAt = LocalDate.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDate createAt) {
		this.createAt = createAt;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public List<DetailConsultation> getItems() {
		return items;
	}

	public void setItems(List<DetailConsultation> items) {
		this.items = items;
	}
	
	public void addItemConsultation(DetailConsultation item) {
		this.items.add(item);
	}

}
